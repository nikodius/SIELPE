<%-- 
    Document   : index
    Created on : 18/10/2016, 09:07:59 AM
    Author     : NicolasRG
--%>

<%@page import="java.util.Base64"%>
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
                <h3 class="panel-title">Candidatos</h3>
            </div>
            <div class="panel-body">
                <div align="right"><a href="GestionarCandidatos?add"><img src="images/add.png" id="imgEX" alt="Agrega Registro"/> Registrar Candidato</a></div>
                <br/>
                <table class = "table table-striped table-bordered table-hover table-condensed" id="tablerPR" >
                    <thead>
                        <tr>
                            <th>Documento Candidato</th>
                            <th>Nombre</th>
                            <th>Genero</th>
                            <th>Fecha Nacimiento</th>
                            <th>Numero Lista</th>
                            <th>Eleccion</th>
                            <th>Imagen</th>
                            <th></th>
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
                            <td><%=pdto.getNombreEleccion()%></td>
                            <td><img src="data:image/png;base64,<%=pdto.getBytesFoto()%>" alt="foto candidato"/></td>
                            <td><a href="GestionarCandidatos?id=<% out.print(pdto.getId());%>"><img src="images/edit.png" id="imgEX" alt="Modificar Registro"/></a></td>
                            <td><a class="open-modal" data-toggle="modal" data-id="<% out.print(pdto.getId());%>" href="#myModal"><img src="images/portrait.png" id="imgEX" alt="Modificar imagen"/ ></a></td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!-- Modal Cargar Foto-->
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"></button>
                    <h4 class="modal-title">Cargar Foto</h4>
                </div>
                <div class="modal-body">
                    <form name="frmImage" id="frmImage" action="" enctype="multipart/form-data" method="post">
                        <div class="row">
                            <div class="form-group">
                                <label for="fotoCandidato" class="col-lg-1 control-label">Foto</label>
                                <div class="col-lg-10">
                                    <input type="file" class="form-control" id="fotoCandidato" name="fotoCandidato"  tabindex="1" accept="image/*" required>
                                </div>
                            </div>
                        </div><br/>
                        <div class="row">
                            <button class="btn btn-success" type="submit" name="enviar" value="Guardar" tabindex="2">Enviar Foto</button>
                        </div>
                    </form> 
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                </div>
            </div>

        </div>
    </div>
    <% }%>
    <br/><br/>
    <script>
        $(document).on("click", ".open-modal", function () {
            var id = $(this).data('id');
            $('#frmImage').attr('action', 'GestionarCandidatos?saveImage='+id);
        });
    </script>
    <jsp:include page="_footer.jsp" />
