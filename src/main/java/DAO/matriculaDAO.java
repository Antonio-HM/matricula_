/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Alumno;
import Entity.Encargado;
import Entity.Grupo;
import Entity.Matricula;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class matriculaDAO extends Conexion {

    
    public String EliminarMatricula(int id) throws Exception {
        String msj = "";
        try {
            this.Conectar();
            String query = "delete from dbomatricula where id_matricula= ?";
            PreparedStatement stm = this.getCnx().prepareStatement(query);
            stm.setInt(1, id);
            int valor = stm.executeUpdate();
            if(valor > 0){
                msj = "Dato Eliminado Correctamente";
            }
        } catch (Exception e) {
            msj = "Error : " + e.getMessage();
        } finally {
            this.Desconectar();
        }
        return msj;
    }
    
    public String catualizarMatricula(Matricula ma) throws Exception {
        String msj = "";
        try {
            this.Conectar();
            String query = "UPdate dbomatricula set anio_matricula=?,fecha_matricula=?,id_alumno=?,id_grupo=? where id_matricula=?";
            PreparedStatement stm = this.getCnx().prepareStatement(query);
            stm.setString(1, ma.getAnio_matricula());
            stm.setString(2, ma.getFecha_matricula());
            stm.setInt(3, ma.getAlumno().getId_alumno());
            stm.setInt(4, ma.getGrupo().getId_grupo());
            stm.setInt(5, ma.getId_matricula());
            int valor = stm.executeUpdate();
            if(valor > 0){
                msj = "Datos Actualizados Correctamente";
            }
        } catch (Exception e) {
            msj = "Error : " + e.getMessage();
        } finally {
            this.Desconectar();
        }
        return msj;
    }
    
    public String InsertarMatricula(Matricula ma) throws Exception {
        String msj = "";
        try {
            this.Conectar();
            String query = "insert into dbomatricula (anio_matricula,fecha_matricula,id_alumno,id_grupo) values (?,?,?,?)";
            PreparedStatement stm = this.getCnx().prepareStatement(query);
            stm.setString(1, ma.getAnio_matricula());
            stm.setString(2, ma.getFecha_matricula());
            stm.setInt(3, ma.getAlumno().getId_alumno());
            stm.setInt(4, ma.getGrupo().getId_grupo());
            int valor = stm.executeUpdate();
            if(valor > 0){
                msj = "Datos Insertados Correctamente";
            }
        } catch (Exception e) {
            msj = "Error : " + e.getMessage();
        } finally {
            this.Desconectar();
        }
        return msj;
    }

    public ArrayList<Matricula> llenarmatricula() throws Exception {

        ArrayList<Matricula> lista = new ArrayList<Matricula>();
        try {
            this.Conectar();
            String Query = "select m.id_matricula,a.nombre,a.apellido,g.nombre_grupo,e.nombre_encargado,m.anio_matricula,m.fecha_matricula\n"
                    + "from dbomatricula m \n"
                    + "inner join dboalumno a on m.id_alumno = a.id_alumno\n"
                    + "inner join dbogrupo g on m.id_grupo = g.id_grupo\n"
                    + "inner join dboencargado e on g.id_encargado = e.id_encargado order by m.id_matricula";
            PreparedStatement smt = this.getCnx().prepareStatement(Query);
            ResultSet rt = smt.executeQuery();
            while (rt.next()) {
                Grupo g = new Grupo();
                Encargado e = new Encargado();
                Alumno a = new Alumno();
                Matricula m = new Matricula();
                m.setId_matricula(rt.getInt("m.id_matricula"));
                a.setNombre(rt.getString("a.nombre"));
                a.setApellido(rt.getString("a.apellido"));
                g.setNombre_grupo(rt.getString("g.nombre_grupo"));
                e.setNombre_encargado(rt.getString("e.nombre_encargado"));
                m.setAnio_matricula(rt.getString("m.anio_matricula"));
                m.setFecha_matricula(rt.getString("m.fecha_matricula"));
                g.setEncargado(e);
                m.setAlumno(a);
                m.setGrupo(g);
                lista.add(m);
                System.out.println("Datos " + rt.getString("a.nombre"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error  " + e.getMessage());
        } finally {
            this.Desconectar();
        }
        return lista;
    }
    
    
    public Matricula llenarPorID(int id) throws Exception {

        Matricula m = new Matricula();
        try {
            this.Conectar();
            String Query = "select * from dbomatricula where id_matricula = ? ";
            PreparedStatement smt = this.getCnx().prepareStatement(Query);
            smt.setInt(1, id);
            ResultSet rt = smt.executeQuery();
            while (rt.next()) {
                Grupo g = new Grupo();
                Alumno a = new Alumno();
                m.setId_matricula(rt.getInt("id_matricula"));
                m.setAnio_matricula(rt.getString("anio_matricula"));
                m.setFecha_matricula(rt.getString("fecha_matricula"));
                a.setId_alumno(rt.getInt("id_alumno"));
                g.setId_grupo(rt.getInt("id_grupo"));
                m.setAlumno(a);
                m.setGrupo(g);
                    
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error  " + e.getMessage());
        } finally {
            this.Desconectar();
        }
        return m;
    }
}
