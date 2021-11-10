<%-- 
    Document   : NuevoMa
    Created on : 12-09-2019, 10:48:19 AM
    Author     : jose.hernandezusam
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/Componentes/menuNav.jsp" />
    <form method="post" action="control?action=saveMatricula">
        <div class="row">
            <div class="col-md-12"><h2>Matricular</h2></div>
        </div>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-3">Año Matricula</div>
            <div class="col-md-3">
                <input type="hidden" name="txtID" value="${alm.id_matricula}" />
                <input type="text" name="txtAnio" class="form-control" value="${alm.anio_matricula}"/>
            </div>
            <div class="col-md-3"></div>
        </div>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-3">Fecha Matricula</div>
            <div class="col-md-3">
                <input type="text" name="txtFecha" class="form-control" value="${alm.fecha_matricula}"/>
            </div>
            <div class="col-md-3"></div>
        </div>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-3">Selecione Alumno</div>
            <div class="col-md-3">
                <select name="cmbAlumno" class="form-control">
                    <c:forEach items="${baseAlu}" var='a'>
                        <c:choose>
                            <c:when test="${a.id_alumno == alm.alumno.id_alumno}">
                                <option value="${a.id_alumno}" selected="">${a.nombre} ${a.apellido}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${a.id_alumno}">${a.nombre} ${a.apellido}</option>
                            </c:otherwise>
                        </c:choose>
                        
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-3"></div>
        </div>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-3">Selecione Grupo</div>
            <div class="col-md-3">
                <select name="cmbGrupo" class="form-control">
                    <c:forEach items="${baseGru}" var='g'>
                        <c:choose>
                            <c:when test="${g.id_grupo == alm.grupo.id_grupo}">
                                <option value="${g.id_grupo}" selected="">${g.nombre_grupo} ${g.encargado.nombre_encargado}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${g.id_grupo}">${g.nombre_grupo} ${g.encargado.nombre_encargado}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-3"></div>
        </div>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-3">

                <input type="submit" name="btnSaa" value="GUARDAR" class="btn btn-block btn-danger" >
            </div>

            <div class="col-md-3"></div>
        </div>
    </form>
</div>
</html>
