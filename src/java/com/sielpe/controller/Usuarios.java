/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sielpe.controller;

import com.sielpe.facade.FachadaDAO;
import com.sielpe.factory.FactoryDTO;
import com.sielpe.utility.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nico
 */
public class Usuarios extends HttpServlet {

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
            ListarUsuarios(request, response);
        } catch (Exception ex) {
            response.sendRedirect("Usuarios?msg=" + ex.getMessage());
        }
    }

    /**
     * peticion listar Usuarios
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void ListarUsuarios(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getQueryString() == null || request.getParameter("msg") != null) {
            ArrayList<Usuarios> listaElecciones = (ArrayList) facadeDAO.listarUsuarios();
            request.setAttribute("listUsuarios", listaElecciones);
            request.getRequestDispatcher("usuarios.jsp").forward(request, response);
        } else {
            redireccionNewUser(request, response);
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
    public void redireccionNewUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("add") != null) {
            request.getRequestDispatcher("nuevoUsuario.jsp").forward(request, response);
        } else {
            nuevoUsuario(request, response);
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
    public void nuevoUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("new") != null) {
            String respuesta = "";
            try {
                System.out.println("FECHA: " + request.getParameter("fechaUsuario"));
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha;
                fecha = format.parse(request.getParameter("fechaUsuario"));
                respuesta = facadeDAO.insertarUsuario(dtoFactory.crearUsuario(request.getParameter("idUsuario"), request.getParameter("generoUsuario"), fecha, request.getParameter("nombreUsuario"), Integer.parseInt(request.getParameter("rolUsuario")), request.getParameter("passUsuario"), request.getParameter("correoUsuario"), Integer.parseInt(request.getParameter("estadoUsuario"))));
                response.sendRedirect("Usuarios?msg=" + respuesta);
            } catch (ParseException ex) {
                respuesta = ex.getMessage();
            }
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
