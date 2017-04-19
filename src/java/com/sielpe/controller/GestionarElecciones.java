/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sielpe.controller;

import com.sielpe.facade.FachadaDAO;
import com.sielpe.factory.FactoryDTO;
import com.sielpe.model.Eleccion;
import com.sielpe.utility.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nico
 */
public class GestionarElecciones extends HttpServlet {

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
        try {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            conexion = Conexion.getInstance();
            facadeDAO = new FachadaDAO();
            dtoFactory = new FactoryDTO();
            ListarElecciones(request, response);
        } catch (Exception ex) {
            response.sendRedirect("GestionarElecciones?msg=" + ex.getMessage());
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
    public void ListarElecciones(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getQueryString() == null || request.getParameter("msg") != null) {
            ArrayList<Eleccion> listaElecciones = (ArrayList) facadeDAO.listarElecciones();
            request.setAttribute("listElecciones", listaElecciones);
            request.getRequestDispatcher("GestionElecciones.jsp").forward(request, response);
        } else {
            redireccionNuevaEleccion(request, response);
        }
    }

    /**
     * peticion redireccion a nuevo usuario
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     * @throws MiExcepcion
     */
    public void redireccionNuevaEleccion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("add") != null) {
            request.getRequestDispatcher("nuevaEleccion.jsp").forward(request, response);
        } else {
            nuevaEleccion(request, response);
        }
    }

    /**
     * peticion crear nuevo usuario
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws MiExcepcion
     * @throws ServletException
     */
    public void nuevaEleccion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("new") != null) {
            String respuesta = "";
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaInicio = format.parse(request.getParameter("fechaInicioInscripcion"));
                Date fechaFin = format.parse(request.getParameter("fechaFinInscripcion"));
                Date fechaVotacion = format.parse(request.getParameter("fechaVotacion"));
                respuesta = facadeDAO.crearEleccion(dtoFactory.crearEleccion(request.getParameter("nombreEleccion"), request.getParameter("descripcionEleccion"), fechaInicio, fechaFin, fechaVotacion, Time.valueOf(request.getParameter("horaInicio") + ":00"), Time.valueOf(request.getParameter("horaFin") + ":00")));
                response.sendRedirect("GestionarElecciones?msg=" + respuesta);
            } catch (ParseException ex) {
                ex.printStackTrace();
                respuesta = ex.getMessage();
            }
        } else {
            redirectEditarEleccion(request, response);
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
    public void redirectEditarEleccion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            Eleccion el = facadeDAO.detallesEleccion(id);
            //tratamiento horas para campos en formulario edit
            String horaInicio = String.valueOf(el.getHoraInicioVotacion());
            horaInicio = horaInicio.substring(0, horaInicio.length()-3); 
            String horaFin = String.valueOf(el.getHoraFinVotacion());
            horaFin = horaFin.substring(0, horaFin.length()-3); 

            request.setAttribute("eleccion", el);
            request.setAttribute("horaInicio", horaInicio);
            request.setAttribute("horaFin", horaFin);
            request.getRequestDispatcher("editarEleccion.jsp").forward(request, response);
        } else {
            actualizarEleccion(request, response);
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
    public void actualizarEleccion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String respuesta = "";
        if (request.getParameter("edit") != null) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaInicio = format.parse(request.getParameter("fechaInicioInscripcion"));
                Date fechaFin = format.parse(request.getParameter("fechaFinInscripcion"));
                Date fechaVotacion = format.parse(request.getParameter("fechaVotacion"));
                respuesta = facadeDAO.editarEleccion(dtoFactory.detallesEleccion(Integer.parseInt(request.getParameter("idEleccion")), request.getParameter("nombreEleccion"), request.getParameter("descripcionEleccion"), fechaInicio, fechaFin, fechaVotacion, Time.valueOf(request.getParameter("horaInicio") + ":00"), Time.valueOf(request.getParameter("horaFin") + ":00")));
                response.sendRedirect("GestionarElecciones?&msg=" + respuesta);
            } catch (ParseException ex) {
                respuesta = ex.getMessage();
            }
        } else {
            response.sendRedirect("GestionarElecciones");
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
