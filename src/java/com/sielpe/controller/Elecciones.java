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
import com.sielpe.model.Usuario;
import com.sielpe.utility.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nico
 */
public class Elecciones extends HttpServlet {
    
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
            response.sendRedirect("Elecciones?msg=" + ex.getMessage());
        }
    }
    
    /**
     * peticion listar Elecciones
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException 
     */
    public void ListarElecciones(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getQueryString() == null || request.getParameter("msg") != null) {
            HttpSession sesion = request.getSession(false);
            Usuario usuario = (Usuario) sesion.getAttribute("user");
            ArrayList<Eleccion> listaElecciones = (ArrayList) facadeDAO.listarElecciones(usuario.getId());
            request.setAttribute("listElecciones", listaElecciones);
            request.getRequestDispatcher("elecciones.jsp").forward(request, response);
        } 
        else{
            redirectCandidatosEleccion(request, response);
        }
    }
    
     /**
     * peticion redireccionar a candidatos eleccion
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException 
     */
    public void redirectCandidatosEleccion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            ArrayList<Candidato> listaCandidatos = (ArrayList) facadeDAO.listarCandidatosEleccion(id);
            request.setAttribute("listCandidatos", listaCandidatos);
            request.getRequestDispatcher("candidatos.jsp").forward(request, response);
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
