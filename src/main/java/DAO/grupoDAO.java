/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Encargado;
import Entity.Grupo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author jose.hernandezusam
 */
public class grupoDAO extends Conexion {

    public ArrayList<Grupo> llenarGRUPO() throws Exception {
        ArrayList<Grupo> lista = new ArrayList<Grupo>();
        try {
            this.Conectar();
            String query = "select g.id_grupo,g.nombre_grupo,e.nombre_encargado \n"
                    + "from dbogrupo g inner join dboencargado e on g.id_encargado = e.id_encargado";
            PreparedStatement smt = this.getCnx().prepareStatement(query);
            ResultSet rt = smt.executeQuery();
            while (rt.next()) {
                Grupo a = new Grupo();
                Encargado e = new Encargado();
                a.setId_grupo(rt.getInt("g.id_grupo"));
                a.setNombre_grupo(rt.getString("g.nombre_grupo"));
                e.setNombre_encargado(rt.getString("e.nombre_encargado"));
                a.setEncargado(e);
                lista.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error " + e.getMessage());
        } finally {
            this.Desconectar();
        }
        return lista;
    }
}
