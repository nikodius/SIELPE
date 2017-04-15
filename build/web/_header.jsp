<%-- 
    Document   : _header
    Created on : 24/10/2016, 03:48:01 PM
    Author     : UserQV
--%>

<%@page import="com.sielpe.model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="css/estilos.css">
        <link type="text/css" rel="stylesheet" href="css/font-awesome.min.css">
        <script src="js/jquery-3.1.1.js"></script>
        <script src="js/bootstrap.js"></script>
        <title>SIELPE</title>
    </head>
    <body>
        <div align="center" id="centrado">
            <header>
                <% response.setHeader("Cache-Control", "no-cache");
                    response.setHeader("Cache-Control", "no-store");
                    response.setDateHeader("Expires", 0);
                    HttpSession sesion = request.getSession(false);
                    Usuario usuario = new Usuario();
                    if (sesion.getAttribute("user") == null) {
                        response.sendRedirect("GestionLogin");
                    } else {
                        usuario = (Usuario) sesion.getAttribute("user");
                    }
                %> 

                <div >   
                    <div class="modal-dialog-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <div class="row">

                                    <div class="col-xs-12 col-sm-2">
                                        <a href="http://www.poli.edu.co/"><img src="images/Logo.png" alt="logo" width="150" height="80"/></a>
                                    </div>
                                    <div class="col-xs-12 col-sm-8">
                                        <h4><strong>SISTEMA ELECTRONICO PARA ELECCIONES</strong></h4>
                                        <h4>SIELPE</h4>
                                    </div>
                                    <% if (usuario.getUserName() != null) {%>
                                    <div class="col-xs-12 col-sm-2">
                                        <div id="datos" align="center" class="">

                                            <span>Bienvenido: <% out.print(usuario.getUserName());%></span><br>
                                            <span><% out.print(usuario.getId());%></span>

                                            <form name="logout" action="Login" id="form1">
                                                <input id="salir" type="submit" name="logout" class="btn btn-danger" value="Cerrar Sesión"/>
                                            </form>
                                        </div>
                                    </div>
                                    <%} else {%>  
                                    <div class="col-xs-12 col-sm-1 btnIngresar">
                                        <a id="aLogin" href="GestionLogin"><i class="fa fa-user" aria-hidden="true"> Ingresar</i></a>
                                    </div>
                                </div>
                                <%}%>       
                            </div> 
                        </div>
                    </div>         
                </div>
            </header>                         

