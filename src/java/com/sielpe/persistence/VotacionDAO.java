/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sielpe.persistence;

import com.sielpe.model.Candidato;
import com.sielpe.model.Usuario;
import com.sielpe.model.Voto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * clase modelo dao usuario
 */
public class VotacionDAO implements ModeloDAO {

    PreparedStatement statement;
    ResultSet rs;

    @Override
    public ArrayList<Object> listarTodo(Connection conexion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object listarUno(Connection conexion, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String editarRegistro(Connection conexion, Object dto, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String crearRegistro(Object dto, Connection conexion) {
        Voto prDto = (Voto) dto;
        int resultado = 0;
        String respuesta = "";
        String sql = "INSERT INTO votacion(id_eleccion, id_user, id_candidato, estado) VALUES (?, ?, ?, ?)";
        try {
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, prDto.getIdEleccion());
            statement.setString(2, prDto.getIdUsuario());
            statement.setString(3, prDto.getIdCandidato());
            statement.setString(4, prDto.getEstado());
            resultado = statement.executeUpdate();
            if (resultado != 0) {
                respuesta = "Voto registrado";

            } else {
                respuesta = "NO se pudo registrar el voto";
            }

        } catch (SQLException ex) {
            System.out.println("Error de MySQL: " + ex.getMessage());
            respuesta = "Error, NO se pudo registrar";
        }
        return respuesta;
    }

    @Override
    public String cambiarEstado(String id, Connection conexion, int estado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Usuario loginValidate(Connection conexion, String document, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
