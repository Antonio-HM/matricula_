<%-- 
    Document   : matricular
    Created on : 12-09-2019, 09:41:31 AM
    Author     : jose.hernandezusam
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/Componentes/menuNav.jsp" />
<h3>${mensaje}</h3>
<div class="table-responsive">
    <table class="table table-active table-bordered table-dark">
        <tr>
            <th>Id</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Grupo</th>
            <th>Encargado</th>
            <th>Año matricula</th>
            <th>Fecha Matricula</th>
            <th></th>
        </tr>
        <c:forEach items="${baseM}" var="m">
            <tr>
                <td>${m.id_matricula}</td>
                <td>${m.alumno.nombre}</td>
                <td>${m.alumno.apellido}</td>
                <td>${m.grupo.nombre_grupo}</td>
                <td>${m.grupo.encargado.nombre_encargado}</td>
                <td>${m.anio_matricula}</td>
                <td>${m.fecha_matricula}</td>
                <th>
                    <a href="control?action=Eliminar&id=${m.id_matricula}" class="btn btn-warning">Eliminar</a>
                </th>
                <th>
                    <a href="control?action=Actualizar&id=${m.id_matricula}" class="btn btn-info">Actualizar</a>
                </th>
            </tr>
        </c:forEach>
    </table>
    <a href="control?action=nuevoMa" class="btn btn-block btn-dark">Nueva Matricula</a>
</div>
<script src="Resources/js/jquery-3.4.1.min.js"></script>
<script src="Resources/js/bootstrap.js"></script>
</div>
</html>
