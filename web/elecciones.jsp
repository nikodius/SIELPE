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

    <!-- miga de pan -->
    <div class="row">
        <ol class="breadcrumb pull-left">
            <li class="active"><a href="#">Elecciones</a></li>
        </ol>
    </div>
    <!-- contenido principal-->
    <div class="row">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Elecciones Vigentes</h3>
            </div>
            <div class="panel-body">
                <%if (request.getAttribute("listElecciones") != null) {
                        List<Eleccion> lista = (ArrayList) request.getAttribute("listElecciones");
                %>
                <br/>
                <table class = "table table-striped table-bordered table-hover table-condensed" id="tablerPR" >
                    <thead>
                        <tr>
                            <th>Nombre Eleccion</th>
                            <th>Descripcion</th>
                            <th>Hora Inicio</th>
                            <th>Hora Fin</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody> 
                        <%
                            for (Eleccion pdto : lista) {
                        %>
                        <tr>
                            <td><%=pdto.getNombre()%></td>
                            <td><%=pdto.getDescripcion()%></td>
                            <td><%=pdto.getHoraInicioVotacion()%></td>
                            <td><%=pdto.getHoraFinVotacion()%></td>
                            <td><a href="Elecciones?id=<% out.print(pdto.getId());%>"><i class="fa fa-search" aria-hidden="true"></i></a></td>
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
