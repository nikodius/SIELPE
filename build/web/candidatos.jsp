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
            <li><a href="Elecciones">Elecciones</a></li>
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
                            <th>Numero Lista</th>
                            <th>Nombre</th>
                            <th>Imagen</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody> 
                        <%
                            for (Candidato pdto : lista) {
                        %>
                        <tr>
                            <td><%=pdto.getNumeroLista()%></td>
                            <td><%=pdto.getNombre()%></td>
                            <td></td>
                            <td><input type="checkbox" class="myinput large" value="<%=pdto.getId()%>" name="select" /></td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <% if (!lista.isEmpty()) {%>
    <div class="row botones">
        <div class="btn-group btn-group-justified" style="padding-left: 20%; padding-right: 20%;" role="group" aria-label="...">
            <div class="btn-group" role="group">
                <form  method="post" action="Votacion">
                    <input type="hidden" name="idCandidato" id="idCandidato" value="">
                    <input type="hidden" name="idEleccion" id="idEleccion" value="<%=lista.get(0).getIdEleccion()%>" required>
                    <input type="hidden" name="idUser" id="idUser" value="<%=usuario.getId()%>" required>
                    <input type="hidden" name="estado" id="estado" value="1" required>
                    <button type="submit" class="btn btn-success">Confirmar Voto</button>
                </form> 
            </div>
            <div class="btn-group" role="group">
                <form  method="post" action="Votacion">
                    <input type="hidden" name="idEleccion" id="idEleccion" value="<%=lista.get(0).getIdEleccion()%>" required>
                    <input type="hidden" name="idUser" id="idUser" value="<%=usuario.getId()%>" required>
                    <input type="hidden" name="estado" id="estado" value="2" required>
                    <button type="submit" class="btn btn-danger">Cancelar Voto</button>
                </form> 
            </div>
        </div>
    </div>
</div>
<%}
        }%>
<br/><br/>
<jsp:include page="_footer.jsp" />
<script>
    $("input:checkbox").on('click', function () {
        var $box = $(this);
        if ($box.is(":checked")) {
            var group = "input:checkbox[name='" + $box.attr("name") + "']";
            $(group).prop("checked", false);
            $box.prop("checked", true);
            $("#idCandidato").val($box.val());
        } else {
            $box.prop("checked", false);
            $("#idCandidato").val("");
        }
    });
</script>
