<%-- 
    Document   : index
    Created on : 18/10/2016, 09:07:59 AM
    Author     : NicolasRG
--%>

<%@page import="com.sielpe.model.Candidato"%>
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
                <li><a href="GestionarElecciones">Elecciones</a></li>
                <li><a class="active" href="GestionarCandidatos">Candidatos</a></li>
                <li><a href="#">Reportes</a></li>
            </ul>
        </div>
    </nav>
    <!-- miga de pan -->
    <div class="row">
        <ol class="breadcrumb pull-left">
            <li class="active"><a href="#">Candidatos</a></li>
        </ol>
    </div>
    <!-- contenido principal-->
    <div class="row">
        <%if (request.getAttribute("listCandidatos") != null) {
                List<Candidato> lista = (ArrayList) request.getAttribute("listCandidatos");
        %>
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title"><%=lista.isEmpty() ? "No hay candidatos" : "Candidatos " + lista.get(0).getNombreEleccion()%></h3>
            </div>
            <div class="panel-body">
                <br/>
                <table class = "table table-striped table-bordered table-hover table-condensed" id="tablerPR" >
                    <thead>
                        <tr>
                            <th>Documento Candidato</th>
                            <th>Nombre</th>
                            <th>Genero</th>
                            <th>Fecha Nacimiento</th>
                            <th>Numero Lista</th>
                            <th>Imagen</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody> 
                        <%
                            for (Candidato pdto : lista) {
                        %>
                        <tr>
                            <td><%=pdto.getId()%></td>
                            <td><%=pdto.getNombre()%></td>
                            <td><%=pdto.getGenero()%></td>
                            <td><%=pdto.getFechaNacimiento()%></td>
                            <td><%=pdto.getNumeroLista()%></td>
                            <td><%=pdto.getFoto()%></td>
                            <td></td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <% }%>
    <br/><br/>
    <jsp:include page="_footer.jsp" />
