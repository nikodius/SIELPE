/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sielpe.persistence;

import com.sielpe.model.Candidato;
import com.sielpe.model.Usuario;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

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
                    + "numero_lista, nombre_eleccion, imagen "
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
                Blob image = rs.getBlob(8);
                candidato.setBytesFoto(image.getBytes(1,(int)image.length()));
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
                    + "numero_lista, nombre_eleccion, imagen "
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
                Blob image = rs.getBlob(8);
                candidato.setBytesFoto(image.getBytes(1,(int)image.length()));
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
        Candidato candidato = new Candidato();
        try {
            String query = "SELECT idcandidato, id_eleccion, nombre, genero, fecha_nacimiento, "
                    + "numero_lista, id_eleccion "
                    + "FROM candidato "
                    + "WHERE idcandidato=?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                candidato.setId(rs.getString(1));
                candidato.setIdEleccion(rs.getInt(2));
                candidato.setNombre(rs.getString(3));
                candidato.setGenero(rs.getString(4));
                candidato.setFechaNacimiento(rs.getDate(5));
                candidato.setNumeroLista(rs.getInt(6));
                candidato.setIdEleccion(rs.getInt(7));
            }
        } catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        //devolvemos el arreglo
        return candidato;
    }

    @Override
    public String editarRegistro(Connection conexion, Object dto) {
        Candidato candidato = (Candidato) dto;
        java.sql.Date sqlDate = new java.sql.Date(candidato.getFechaNacimiento().getTime());
        int resultado = 0;
        String respuesta = "";
        String sql = "UPDATE candidato SET nombre=?, genero=?, fecha_nacimiento=?, "
                    + "numero_lista=?, id_eleccion=? "
                + "WHERE idcandidato=?;";
        try {
            statement = conexion.prepareStatement(sql);
            statement.setString(1, candidato.getNombre());
            statement.setString(2, candidato.getGenero());
            statement.setDate(3, sqlDate);
            statement.setInt(4, candidato.getNumeroLista());
            statement.setInt(5, candidato.getIdEleccion());
            statement.setString(6, candidato.getId());

            resultado = statement.executeUpdate();
            
            //comprobar si se ejecuto la instruccion en sql
            if (resultado != 0) {
                respuesta = "Modificado Correctamente";

            } else {
                respuesta = "NO se ha modificado";
            }

        } catch (SQLException ex) {
            System.out.println("Error de MySQL: " + ex.getMessage());
            respuesta = "error, no se modifico";
        }
        return respuesta;
    }
    
    public String guardarFoto(Connection conexion, byte[] foto, String id) {
        int resultado = 0;
        String respuesta = "";
        String sql = "UPDATE candidato SET imagen=? "
                + "WHERE idcandidato=?;";
        try {
            statement = conexion.prepareStatement(sql);
            statement.setBytes(1, foto);
            statement.setString(2, id);

            resultado = statement.executeUpdate();
            
            //comprobar si se ejecuto la instruccion en sql
            if (resultado != 0) {
                respuesta = "Foto Almacenada";

            } else {
                respuesta = "NO se ha almacenado";
            }

        } catch (SQLException ex) {
            System.out.println("Error de MySQL: " + ex.getMessage());
            respuesta = "error, no se almaceno";
        }
        return respuesta;
    }

    @Override
    public String crearRegistro(Object dto, Connection conexion) {
        Candidato candidato = (Candidato) dto;
        java.sql.Date sqlDate = new java.sql.Date(candidato.getFechaNacimiento().getTime());
        String rta = "";
        try {
            statement = conexion.prepareStatement("INSERT INTO candidato(idcandidato, nombre, genero, fecha_nacimiento, "
                    + "numero_lista, id_eleccion, imagen) VALUES (?,?,?,?,?,?,?)");
            statement.setString(1, candidato.getId());
            statement.setString(2, candidato.getNombre());
            statement.setString(3, candidato.getGenero());
            statement.setDate(4, sqlDate);
            statement.setInt(5, candidato.getNumeroLista());
            statement.setInt(6, candidato.getIdEleccion());
            statement.setBytes(7, candidato.getBytesFoto());
            int resultado = statement.executeUpdate();
            if (resultado == 0) {
                rta = "No se pudo registrar el candidato";//Mensaje No.6

            } else {
                rta = "Candidato Registrado";//Mensaje No.5
            }

        } catch (SQLException sqle) {
            rta = sqle.getMessage();
        }
        return rta;
    }

    @Override
    public String cambiarEstado(String id, Connection conexion, int estado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Usuario loginValidate(Connection conexion, String document, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
