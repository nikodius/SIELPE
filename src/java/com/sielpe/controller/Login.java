/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sielpe.controller;

import com.sielpe.facade.FachadaDAO;
import com.sielpe.factory.FactoryDTO;
import com.sielpe.model.Usuario;
import com.sielpe.utility.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author NiKo
 */
public class Login extends HttpServlet {

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
            redireccionLogin(request, response);
        } catch (Exception ex) {
            response.sendRedirect("Login?msg=" + ex.getMessage());
        }
    }

    /**
     * peticion redireccionar al login
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     * @throws MiExcepcion
     * @throws SQLException
     * @throws NamingException
     */
    public void redireccionLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException, NamingException {
        if (request.getQueryString() == null || request.getParameter("msg") != null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            login(request, response);
        }
    }

    /**
     * peticion validar usuario
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     * @throws MiExcepcion
     * @throws SQLException
     * @throws NamingException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException, NamingException {
        if (request.getParameter("enviar") != null) {
            String respuesta = "";
            Usuario user = facadeDAO.validateUser(request.getParameter("nombreUsuario"), request.getParameter("contraseniaUsuario"));
            usuarioValido : if (user != null) {
                if (user.getIdEstado() != 1) {
                    respuesta = "Usuario invalido o inactivo";
                    response.sendRedirect("Login?msg=" + respuesta);
                    break usuarioValido;
                }
                HttpSession sesion = request.getSession(true);
                sesion.setAttribute("user", user);
                response.sendRedirect(user.getIdRol() == 1 ? "Usuarios" : "Elecciones");
            } else {
                respuesta = "Datos de usuario incorrectos";
                response.sendRedirect("Login?msg=" + respuesta);
            }
        } else {
            logout(request, response);
        }
    }

    /**
     * peticion cerrar sesion
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("logout") != null) {
            HttpSession sesion = request.getSession(false);
            sesion.invalidate();
            sesion = null;
            response.sendRedirect("index.jsp");
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
