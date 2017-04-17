/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sielpe.persistence;

import com.sielpe.model.Candidato;
import com.sielpe.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * clase modelo dao usuario
 */
public class CandidatoDAO implements ModeloDAO {

    PreparedStatement statement;
    ResultSet rs;

    @Override
    public ArrayList<Object> listarTodo(Connection conexion) {
        ArrayList<Object> listaCandidatos = new ArrayList();
        try {
            String query = "SELECT idcandidato, id_eleccion, nombre, genero, fecha_nacimiento, "
                    + "numero_lista, nombre_eleccion "
                    + "FROM candidato "
                    + "INNER JOIN eleccion ON(candidato.id_eleccion=eleccion.ideleccion)";
            statement = conexion.prepareStatement(query);
            rs = statement.executeQuery();
            while (rs.next()) {
                Candidato candidato = new Candidato();
                candidato.setId(rs.getString(1));
                candidato.setIdEleccion(rs.getInt(2));
                candidato.setNombre(rs.getString(3));
                candidato.setGenero(rs.getString(4));
                candidato.setFechaNacimiento(rs.getDate(5));
                candidato.setNumeroLista(rs.getInt(6));
                candidato.setNombreEleccion(rs.getString(7));
                //candidato.setImagen(rs.getImage(8));
                listaCandidatos.add(candidato);
            }
        } catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        //devolvemos el arreglo
        return listaCandidatos;
    }

    public ArrayList<Object> listarTodo(Connection conexion, int id) {
        ArrayList<Object> listaCandidatos = new ArrayList();
        try {
            String query = "SELECT idcandidato, id_eleccion, nombre, genero, fecha_nacimiento, "
                    + "numero_lista, nombre_eleccion "
                    + "FROM candidato "
                    + "INNER JOIN eleccion ON(candidato.id_eleccion=eleccion.ideleccion)"
                    + "WHERE id_eleccion=?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                Candidato candidato = new Candidato();
                candidato.setId(rs.getString(1));
                candidato.setIdEleccion(rs.getInt(2));
                candidato.setNombre(rs.getString(3));
                candidato.setGenero(rs.getString(4));
                candidato.setFechaNacimiento(rs.getDate(5));
                candidato.setNumeroLista(rs.getInt(6));
                candidato.setNombreEleccion(rs.getString(7));
                //candidato.setImagen(rs.getImage(8));
                listaCandidatos.add(candidato);
            }
        } catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        //devolvemos el arreglo
        return listaCandidatos;
    }

    @Override
    public Object listarUno(Connection conexion, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String editarRegistro(Connection conexion, Object dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String crearRegistro(Object dto, Connection conexion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String cambiarEstado(String id, Connection conexion, int estado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Usuario loginValidate(Connection conexion, String document, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
