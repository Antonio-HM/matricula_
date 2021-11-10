/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Alumno;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author jose.hernandezusam
 */
public class alumnoDAO extends Conexion{
    
    public ArrayList<Alumno> llenarAlumno() throws Exception{
        ArrayList<Alumno> lista =new ArrayList<Alumno>();
        try {
            this.Conectar();
            String query = "SElect id_alumno,nombre,apellido from dboalumno";
            PreparedStatement smt = this.getCnx().prepareStatement(query);
            ResultSet rt = smt.executeQuery();
            while(rt.next()){
                Alumno a = new Alumno();
                a.setId_alumno(rt.getInt("id_alumno"));
                a.setNombre(rt.getString("nombre"));
                a.setApellido(rt.getString("apellido"));
                lista.add(a);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error " + e.getMessage());
        }finally{
            this.Desconectar();
        }
        return lista;
    }
}
