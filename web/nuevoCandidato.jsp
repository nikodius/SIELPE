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
                <li><a href="GestionarElecciones">Elecciones</a></li>
                <li><a class="active" href="GestionarCandidatos">Candidatos</a></li>
                <li><a href="#">Reportes</a></li>
            </ul>
        </div>
    </nav>
    <!-- miga de pan -->
    <div class="row">
        <ol class="breadcrumb pull-left">
            <li><a href="GestionarCandidatos">Candidatos</a></li>
            <li class="active"><a href="#">Registro Candidato</a></li>
        </ol>
    </div>
    <!-- contenido principal-->
    <div class="row">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Registro Candidato</h3>
            </div>
            <div class="panel-body">
                <form  method="post" action="GestionarCandidatos?new">
                    <div class="row">
                        <div class="form-group">
                            <label for="idCandidato" class="col-lg-1 control-label" >Numero Identificación</label>
                            <div class="col-lg-5">
                                <input type="text" class="form-control" id="idCandidato" name="idCandidato" placeholder="Numero Identificación" tabindex="1" required>
                            </div>
                            <label for="nombreCandidato" class="col-lg-1 control-label">Nombre Completo</label>
                            <div class="col-lg-5">
                                <input type="text" class="form-control" id="nombreCandidato" name="nombreCandidato" placeholder="Nombre Completo" tabindex="2" required>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="fechaCandidato" class="col-lg-1 control-label">Fecha Nacimiento</label>
                            <div class="col-lg-5">
                                <input type="date" class="form-control" id="fechaCandidato" name="fechaCandidato" tabindex="3" required>
                            </div>
                            <label for="generoCandidato" class="col-lg-1 control-label">Genero</label>
                            <div class="col-lg-5">
                                <input type="radio" name="generoCandidato" id="generoCandidato" value="Masculino" tabindex="4" required> Masculino<br>
                                <input type="radio" name="generoCandidato" id="generoCandidato" value="Femenino" tabindex="5" required> Femenino<br>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="eleccionCandidato" class="col-lg-1 control-label">Eleccion</label>
                            <div class="col-lg-5">
                                <select class="form-control" id="eleccionCandidato" name="eleccionCandidato" tabindex="6" required>
                                    <%if (request.getAttribute("eleccionesVigentes") != null) {
                                            List<Eleccion> lista = (ArrayList) request.getAttribute("eleccionesVigentes");
                                            for (Eleccion el : lista) {%>
                                    <option value="<%=el.getId()%>"><%=el.getNombre()%></option>
                                    <% }
                                    }%>
                                </select>   
                            </div>
                            <label for="listaCandidato" class="col-lg-1 control-label">Numero Lista</label>
                            <div class="col-lg-5">
                                <input type="number" class="form-control" id="listaCandidato" name="listaCandidato" placeholder="Numero Lista" tabindex="7" required>
                            </div>
                        </div>
                    </div> <br/>
                    <br>
                    <div class="contenedor-botones">
                        <button class="btn btn-success" type="submit" name="enviar" value="Guardar" tabindex="9">Registrar Candidato</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="_footer.jsp" />
