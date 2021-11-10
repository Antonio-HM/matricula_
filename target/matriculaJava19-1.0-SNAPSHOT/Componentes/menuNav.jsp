<%-- 
    Document   : menuNav
    Created on : 12-09-2019, 09:44:29 AM
    Author     : jose.hernandezusam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="Resources/css/bootstrap.css" />
    </head>
    <body>
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-light bg-danger">
                <a class="navbar-brand" href="control?action=menu">Menu</a>
                <a class="navbar-brand" href="control?action=matricular">Matricular</a>
                <a class="navbar-brand" href="control?action=grupo">Grupos</a>
                <a class="navbar-brand" href="control?action=alumno">Alumnos</a>
            </nav>
