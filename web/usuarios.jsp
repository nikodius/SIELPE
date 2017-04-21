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
            <li class="active"><a href="#">Usuarios</a></li>
        </ol>
    </div>
    <!-- contenido principal-->
    <div class="row">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Usuarios</h3>
            </div>
            <div class="panel-body">
                <%if (request.getAttribute("listUsuarios") != null) {
                        List<Usuario> lista = (ArrayList) request.getAttribute("listUsuarios");
                %>
                <div align="right"><a href="Usuarios?add"><img src="images/add.png" id="imgEX" alt="Agrega Registro"/> Nuevo Usuario</a></div>
                <br/>
                <table class = "table table-striped table-bordered table-hover table-condensed col-xs-12 col-sm-8" id="tablerPR" >
                    <thead>
                        <tr>
                            <th>Id Usuario</th>
                            <th>Nombre Usuario</th>
                            <th>Fecha Nacimiento</th>
                            <th>Rol</th>
                            <th>Genero</th>
                            <th>Email</th>
                            <th>Estado</th>
                            <th> </th>
                        </tr>
                    </thead>
                    <tbody> 
                        <%
                            for (Usuario pdto : lista) {
                        %>
                        <tr>
                            <td><%=pdto.getId()%></td>
                            <td><%=pdto.getUserName()%></td>
                            <td><%=pdto.getFechaNacimiento()%></td>
                            <td><%=pdto.getNombreRol()%></td>
                            <td><%=pdto.getGenero()%></td>
                            <td><%=pdto.getEmail()%></td>
                            <td><%=pdto.getEstado()%></td>
                            <td><a href="Usuarios?id=<% out.print(pdto.getId());%>"><img src="images/edit.png" id="imgEX" alt="Modificar Registro"/></a></td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
                <%}%>
            </div>
        </div>
    </div>
</div>

<jsp:include page="_footer.jsp" />
