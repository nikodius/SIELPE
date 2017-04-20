/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sielpe.controller;

import com.sielpe.facade.FachadaDAO;
import com.sielpe.factory.FactoryDTO;
import com.sielpe.model.Candidato;
import com.sielpe.model.Eleccion;
import com.sielpe.utility.Conexion;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Nico
 */
public class GestionarCandidatos extends HttpServlet {

    FachadaDAO facadeDAO;
    FactoryDTO dtoFactory;
    Connection conexion;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            conexion = Conexion.getInstance();
            facadeDAO = new FachadaDAO();
            dtoFactory = new FactoryDTO();
            ListarCandidatos(request, response);
        } catch (Exception ex) {
            response.sendRedirect("GestionarCandidatos?msg=" + ex.getMessage());
        }
    }

    /**
     * peticion listar Elecciones
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void ListarCandidatos(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getQueryString() == null || request.getParameter("msg") != null) {
            ArrayList<Candidato> listaCandidatos = (ArrayList) facadeDAO.listarCandidatos();
            request.setAttribute("listCandidatos", listaCandidatos);
            request.getRequestDispatcher("GestionCandidatos.jsp").forward(request, response);
        } else {
            redireccionNuevoCandidato(request, response);
        }
    }

    /**
     * peticion redireccion a nuevo candidato
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     * @throws MiExcepcion
     */
    public void redireccionNuevoCandidato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("add") != null) {
            ArrayList<Eleccion> eleccionesVigentes = (ArrayList) facadeDAO.eleccionesVigentes();
            request.setAttribute("eleccionesVigentes", eleccionesVigentes);
            request.getRequestDispatcher("nuevoCandidato.jsp").forward(request, response);
        } else {
            nuevoCandidato(request, response);
        }
    }

    /**
     * peticion crear nuevo candidato
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws MiExcepcion
     * @throws ServletException
     */
    public void nuevoCandidato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("new") != null) {
            String respuesta = "";
            byte[] bytes = null;
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha = format.parse(request.getParameter("fechaCandidato"));
                respuesta = facadeDAO.crearCandidato(dtoFactory.crearCandidato(request.getParameter("idCandidato"), request.getParameter("generoCandidato"), fecha, request.getParameter("nombreCandidato"), Integer.parseInt(request.getParameter("listaCandidato")), Integer.parseInt(request.getParameter("eleccionCandidato")), bytes));
            } catch (ParseException ex) {
                respuesta = ex.getMessage();
            }
            response.sendRedirect("GestionarCandidatos?msg=" + respuesta);
        } else {
            guardarFoto(request, response);
        }
    }

    /**
     * peticion crear nuevo candidato
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws MiExcepcion
     * @throws ServletException
     */
    public void guardarFoto(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("saveImage") != null) {
            String respuesta = "";
            String id = request.getParameter("saveImage");
            byte[] bytes = null;
            try {
                //procesando foto
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload sfu = new ServletFileUpload(factory);
                List items = sfu.parseRequest(request);
                Iterator iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (!item.isFormField()) {
                        bytes = item.get();
                    }
                }
                respuesta = facadeDAO.fotoCandidato(bytes, id);
            } catch (FileUploadException ex) {
                respuesta = ex.getMessage();
            }
            response.sendRedirect("GestionarCandidatos?msg=" + respuesta);
        } else {
            redirectEditarCandidato(request, response);
        }
    }

    /**
     * peticion redireccionar editar usuario
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws MiExcepcion
     * @throws ServletException
     */
    public void redirectEditarCandidato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            Candidato us = facadeDAO.detallesCandidato(id);
            request.setAttribute("candidato", us);
            ArrayList<Eleccion> eleccionesVigentes = (ArrayList) facadeDAO.eleccionesVigentes();
            request.setAttribute("eleccionesVigentes", eleccionesVigentes);
            request.getRequestDispatcher("editarCandidato.jsp").forward(request, response);
        } else {
            actualizarUsuario(request, response);
        }
    }

    /**
     * peticion para actualizar un usuario
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws MiExcepcion
     */
    public void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String respuesta = "";
        if (request.getParameter("edit") != null) {
            byte[] bytes = null;
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha = format.parse(request.getParameter("fechaCandidato"));
                respuesta = facadeDAO.editarCandidato(dtoFactory.crearCandidato(request.getParameter("idCandidato"), request.getParameter("generoCandidato"), fecha, request.getParameter("nombreCandidato"), Integer.parseInt(request.getParameter("listaCandidato")), Integer.parseInt(request.getParameter("eleccionCandidato")), bytes));
                response.sendRedirect("GestionarCandidatos?&msg=" + respuesta);
            } catch (ParseException ex) {
                respuesta = ex.getMessage();
            }
        } else {
            response.sendRedirect("GestionarCandidatos");
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
