<%-- 
    Document   : index
    Created on : 18/10/2016, 09:07:59 AM
    Author     : NicolasRG
--%>

<%@page import="com.sielpe.model.Eleccion"%>
<%@page import="com.sielpe.model.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="_header.jsp" />
<% response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setDateHeader("Expires", 0);
    HttpSession sesion = request.getSession(false);
    Usuario usuario = new Usuario();
    if (sesion.getAttribute("user") == null) {
        response.sendRedirect("Login");
    } else {
        usuario = (Usuario) sesion.getAttribute("user");
        if (usuario.getIdRol() != 1) {
            response.sendRedirect("Login");
        }
    }
%> 
<link rel="stylesheet" href="css/jquery.dataTables.min.css">
<script src="js/jquery.dataTables.min.js"></script>
<script>
    $(document).ready(function () {
        $('#tablerPR').DataTable({
            language: {
                url: 'js/Spanish.json'
            }
        });
        $("#mensaje").fadeOut(7000);
    });
</script>
<div class="row cajaPrincipal">
    <% if (request.getParameter("msg") != null) { %>
    <div id="mensaje" align="center" class="alert alert-info"><%out.print(request.getParameter("msg"));%></div>
    <% }%>

    <!-- menu -->
    <nav class="navbar navbar-default">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <span class="navbar-brand visible-xs">Menú</span>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul>
                <li><a class="active" href="Usuarios">Usuarios</a></li>
                <li><a href="GestionarElecciones">Elecciones</a></li>
                <li><a href="GestionarCandidatos">Candidatos</a></li>
                <li><a href="#">Reportes</a></li>
            </ul>
        </div>
    </nav>
    <!-- miga de pan -->
    <div class="row">
        <ol class="breadcrumb pull-left">
            <li><a href="Usuarios">Usuarios</a></li>
            <li class="active"><a href="#">Nuevo Usuario</a></li>
        </ol>
    </div>
    <!-- contenido principal-->
    <div class="row">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Nuevo Usuario</h3>
            </div>
            <div class="panel-body">
                <form  method="post" action="Usuarios?new">
                    <div class="row">
                        <div class="form-group">
                            <label for="idUsuario" class="col-lg-1 control-label" >Documento Identidad</label>
                            <div class="col-lg-5">
                                <input type="text" class="form-control" id="idUsuario" name="idUsuario" placeholder="Documento Identidad" tabindex="1" required>
                            </div>
                            <label for="nombreUsuario" class="col-lg-1 control-label">Nombre Usuario</label>
                            <div class="col-lg-5">
                                <input type="text" class="form-control" id="nombreUsuario" name="nombreUsuario" placeholder="Nombre Usuario" tabindex="2" required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="fechaUsuario" class="col-lg-1 control-label">Fecha Nacimiento</label>
                            <div class="col-lg-5">
                                <input type="date" class="form-control" id="fechaUsuario" name="fechaUsuario" tabindex="3" required>
                            </div>
                            <label for="generoUsuario" class="col-lg-1 control-label">Genero</label>
                            <div class="col-lg-5">
                                <input type="radio" name="generoUsuario" id="generoUsuario" value="Masculino" tabindex="4" required> Masculino<br>
                                <input type="radio" name="generoUsuario" id="generoUsuario" value="Femenino" tabindex="5" required> Femenino<br>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="estadoUsuario" class="col-lg-1 control-label">Estado</label>
                            <div class="col-lg-5">
                                <select class="form-control" id="estadoUsuario" name="estadoUsuario" tabindex="6" required>
                                    <option value="1">Activo</option>
                                    <option value="2">Inactivo</option>
                                </select>   
                            </div>
                            <label for="rolUsuario" class="col-lg-1 control-label">Rol</label>
                            <div class="col-lg-5">
                                <select class="form-control" id="rolUsuario" name="rolUsuario" tabindex="7" required="">
                                    <option value="2">Usuario</option>
                                    <option value="1">Administrador</option>
                                </select>
                            </div>
                        </div>
                    </div> <br/>
                    <div class="row">
                        <div class="form-group">
                            <label for="correoUsuario" class="col-lg-1 control-label">Correo Electronico</label>
                            <div class="col-lg-5">
                                <input type="email" class="form-control" id="correoUsuario" name="correoUsuario" placeholder="Correo Electronico" tabindex="8" required>
                            </div>
                            <label for="passUsuario" class="col-lg-1 control-label">Contraseña</label>
                            <div class="col-lg-5">
                                <input type="password" class="form-control" id="passUsuario" name="passUsuario" placeholder="Contraseña" tabindex="9" required>
                            </div>
                        </div>
                    </div><br>
                    <div class="contenedor-botones">
                        <button class="btn btn-success" type="submit" name="enviar" value="Guardar" tabindex="9">Nuevo Usuario</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="_footer.jsp" />
