<%@page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64;"%>
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
                <table class = "table table-striped table-bordered table-hover table-condensed col-xs-12 col-sm-8" id="tablerPR" >
                    <thead>
                        <tr>
                            <th>Numero Lista</th>
                            <th>Nombre</th>
                            <th>Foto</th>
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
                            <td> <%if (pdto.getBytesFoto() != null) {%>
                                <img src="data:image/png;base64,<%=Base64.encode(pdto.getBytesFoto())%>" width="120" alt="foto candidato"/>
                                <% }%>
                            </td>
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
                <button type="button" class="btn btn-success" data-toggle="modal" data-target="#modalConfirm">Confirmar Voto</button>
            </div>
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modalCancel">Cancelar Voto</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal confirmar voto-->
<div class="modal fade" id="modalConfirm" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"></button>
                <h4 class="modal-title">Confirmar Voto</h4>
            </div>
            <div class="modal-body">
                <p>¿Está seguro de enviar su voto? No podra acceder de nuevo a esta elección, si no selecciono ningun candidato sera tomado como <b>voto en blanco</b>. 
                </p>
            </div>
            <div class="modal-footer">
                <div class="form-inline">
                    <div class="form-group">
                        <form  method="post" action="Votacion">
                            <input type="hidden" name="idCandidato" id="idCandidato" value="">
                            <input type="hidden" name="idEleccion" id="idEleccion" value="<%=lista.get(0).getIdEleccion()%>" >
                            <input type="hidden" name="idUser" id="idUser" value="<%=usuario.getId()%>" >
                            <input type="hidden" name="estado" id="estado" value="1" >
                            <button type="submit" class="btn btn-primary">Confirmar Voto</button>
                        </form> 
                    </div>
                    <div class="form-group">
                        <button type="button" data-dismiss="modal" class="btn">Cancelar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal cancelar voto-->
<div class="modal fade" id="modalCancel" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"></button>
                <h4 class="modal-title">Cancelar Voto</h4>
            </div>
            <div class="modal-body">
                <p>¿Está seguro de cancelar su voto? Su voto sera marcado como cancelado y no podra acceder de nuevo a esta elección. 
                </p>
            </div>
            <div class="modal-footer">
                <div class="form-inline">
                    <div class="form-group">
                        <form  method="post" action="Votacion">
                            <input type="hidden" name="idEleccion" id="idEleccion" value="<%=lista.get(0).getIdEleccion()%>" >
                            <input type="hidden" name="idUser" id="idUser" value="<%=usuario.getId()%>" >
                            <input type="hidden" name="estado" id="estado" value="2" >
                            <button type="submit" class="btn btn-danger">Cancelar Voto</button>
                        </form> 
                    </div>
                    <div class="form-group">
                        <button type="button" data-dismiss="modal" class="btn">Cancelar</button>
                    </div>
                </div>
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
