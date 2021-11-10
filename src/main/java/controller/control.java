/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.alumnoDAO;
import DAO.grupoDAO;
import DAO.matriculaDAO;
import Entity.Alumno;
import Entity.Encargado;
import Entity.Grupo;
import Entity.Matricula;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "control", urlPatterns = {"/control"})
public class control extends HttpServlet {

    matriculaDAO maDAO;
    alumnoDAO aluDAO;
    grupoDAO gruDAO;

    public control() {
        super();
        maDAO = new matriculaDAO();
        aluDAO = new alumnoDAO();
        gruDAO = new grupoDAO();
    }

    private RequestDispatcher dis;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String ruta = "";
        String action = request.getParameter("action");
        switch (action) {
            case "menu":
                ruta = "View/menu.jsp";
                this.menu(request, response, ruta);
                break;
            case "matricular":
                ruta = "View/matricular.jsp";
                this.matricular(request, response, ruta);
                break;
            case "grupo":
                ruta = "View/grupo.jsp";
                this.grupo(request, response, ruta);
                break;
            case "alumno":
                ruta = "View/alumno.jsp";
                this.alumno(request, response, ruta);
                break;
            case "nuevoMa":
                ruta = "View/NuevoMa.jsp";
                this.nuevaMatricula(request, response, ruta);
                break;
            case "saveMatricula":
                ruta = "View/matricular.jsp";
                this.insertarMatricula(request, response, ruta);
                break;
            case "Eliminar":
                ruta = "View/matricular.jsp";
                this.eliminarMatricula(request, response, ruta);
                break;
            case "Actualizar":
                ruta = "View/NuevoMa.jsp";
                this.cargarIDMatricula(request, response, ruta);
                break;
        }
    }

    protected void cargarIDMatricula(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {

        int idActualiza = Integer.parseInt(request.getParameter("id"));
        try {
            request.setAttribute("alm", maDAO.llenarPorID(idActualiza));
            request.setAttribute("baseAlu", aluDAO.llenarAlumno());
            request.setAttribute("baseGru", gruDAO.llenarGRUPO());
        } catch (Exception e) {
            e.printStackTrace();
        }

        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    protected void eliminarMatricula(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {

        int idEliminar = Integer.parseInt(request.getParameter("id"));
        try {
            String msj = maDAO.EliminarMatricula(idEliminar);
            String alert = "<div class='alert alert-warning alert-dismissible fade show' role='alert' "
                    + "  <strong>"+msj+"!</strong>. "
                    + "  <button type='button' class='close' data-dismiss='alert' aria-label='Close'> "
                    + "    <span aria-hidden='true'>&times;</span> "
                    + "  </button> "
                    + "</div>";
            request.setAttribute("mensaje", alert);
            request.setAttribute("baseM", maDAO.llenarmatricula());
        } catch (Exception e) {
            e.printStackTrace();
        }

        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    protected void insertarMatricula(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {

        Matricula ma = new Matricula();
        Alumno al = new Alumno();
        Grupo gr = new Grupo();
        String idDAto = request.getParameter("txtID");
        ma.setAnio_matricula(request.getParameter("txtAnio"));
        ma.setFecha_matricula(request.getParameter("txtFecha"));
        al.setId_alumno(Integer.parseInt(request.getParameter("cmbAlumno")));
        gr.setId_grupo(Integer.parseInt(request.getParameter("cmbGrupo")));
        ma.setAlumno(al);
        ma.setGrupo(gr);
        try {
            String msj = "";
            if (idDAto == null || idDAto.isEmpty()) {
                msj = maDAO.InsertarMatricula(ma);
            } else {
                ma.setId_matricula(Integer.parseInt(idDAto));
                msj = maDAO.catualizarMatricula(ma);
            }
            String alert = "<div class='alert alert-warning alert-dismissible fade show' role='alert' "
                    + "  <strong>"+msj+"!</strong>. "
                    + "  <button type='button' class='close' data-dismiss='alert' aria-label='Close'> "
                    + "    <span aria-hidden='true'>&times;</span> "
                    + "  </button> "
                    + "</div>";
            request.setAttribute("mensaje", alert);
            request.setAttribute("baseM", maDAO.llenarmatricula());
        } catch (Exception e) {
            e.printStackTrace();
        }

        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    protected void nuevaMatricula(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        try {
            request.setAttribute("baseAlu", aluDAO.llenarAlumno());
            request.setAttribute("baseGru", gruDAO.llenarGRUPO());
        } catch (Exception e) {
        }
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    protected void menu(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    protected void matricular(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {

        try {
            request.setAttribute("baseM", maDAO.llenarmatricula());
        } catch (Exception e) {
            e.printStackTrace();
        }
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    protected void grupo(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    protected void alumno(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        dis = request.getRequestDispatcher(ruta);
        dis.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
