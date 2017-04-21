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
                <li><a href="Usuarios">Usuarios</a></li>
                <li><a class="active" href="GestionarElecciones">Elecciones</a></li>
                <li><a href="GestionarCandidatos">Candidatos</a></li>
                <li><a href="#">Reportes</a></li>
            </ul>
        </div>
    </nav>
    <!-- miga de pan -->
    <div class="row">
        <ol class="breadcrumb pull-left">
            <li><a href="GestionarElecciones">Elecciones</a></li>
            <li class="active"><a href="#">Nueva Eleccion</a></li>
        </ol>
    </div>
    <!-- contenido principal-->
    <div class="row">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Nueva Eleccion</h3>
            </div>
            <div class="panel-body">
                <form id="formu" method="post" action="GestionarElecciones?new">
                    <div class="row">
                        <div class="form-group">
                            <label for="nombreEleccion" class="col-lg-1 control-label" >Nombre</label>
                            <div class="col-lg-5">
                                <input type="text" class="form-control" id="nombreEleccion" name="nombreEleccion" placeholder="Nombre" tabindex="1" maxlength="44" required>
                            </div>
                            <label for="descripcionEleccion" class="col-lg-1 control-label">Descripción</label>
                            <div class="col-lg-5">
                                <input type="text" class="form-control" id="descripcionEleccion" name="descripcionEleccion" placeholder="Descripción" tabindex="2" maxlength="240" required>
                            </div>
                        </div>
                    </div><br/>
                    <div class="row">
                        <div class="form-group">
                            <label for="fechaInicioInscripcion" class="col-lg-1 control-label">Fecha Inicio Inscripcion</label>
                            <div class="col-lg-5">
                                <input type="date" class="form-control" id="fechaInicioInscripcion" name="fechaInicioInscripcion" tabindex="3" required>
                            </div>
                            <label for="fechaFinInscripcion" class="col-lg-1 control-label">Fecha Fin Inscripcion</label>
                            <div class="col-lg-5">
                                <input type="date" class="form-control" id="fechaFinInscripcion" name="fechaFinInscripcion" tabindex="4" required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="fechaVotacion" class="col-lg-1 control-label">Fecha Votacion</label>
                            <div class="col-lg-5">
                                <input type="date" class="form-control" id="fechaVotacion" name="fechaVotacion" tabindex="5" required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="horaInicio" class="col-lg-1 control-label">Hora Inicio Votacion</label>
                            <div class="col-lg-5">
                                <input type="time" class="form-control" id="horaInicio" name="horaInicio" tabindex="6" required>
                            </div>
                            <label for="horaFin" class="col-lg-1 control-label">Hora Fin Votacion</label>
                            <div class="col-lg-5">
                                <input type="time" class="form-control" id="horaFin" name="horaFin" tabindex="7" required>
                            </div>
                        </div>
                    </div><br>
                    <div class="contenedor-botones">
                        <button class="btn btn-success" type="submit" name="enviar" value="Guardar" tabindex="9">Nueva Eleccion</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
$("#formu").validate();
</script>
<jsp:include page="_footer.jsp" />
