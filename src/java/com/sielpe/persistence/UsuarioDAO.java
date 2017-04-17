/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sielpe.persistence;

import com.sielpe.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * clase modelo dao usuario
 */
public class UsuarioDAO implements ModeloDAO {

    PreparedStatement statement;
    ResultSet rs;

    @Override
    public ArrayList<Object> listarTodo(Connection conexion) {
        ArrayList<Object> listaUsuarios = new ArrayList();
        try {
            String query = "SELECT nro_documento_user, nombre_user, fecha_nacimiento_user, id_rol, nombre_rol, genero, "
                    + "email, id_estado, nombre_estado "
                    + "FROM usuario "
                    + "INNER JOIN rol ON(usuario.id_rol=rol.idrol)"
                    + "INNER JOIN estado_usuarios ON(usuario.id_estado=estado_usuarios.Id)";
            statement = conexion.prepareStatement(query);
            rs = statement.executeQuery();
            while (rs.next()) {
                Usuario el = new Usuario();
                el.setId(rs.getString(1));
                el.setUserName(rs.getString(2));
                el.setFechaNacimiento(rs.getDate(3));
                el.setIdRol(rs.getInt(4));
                el.setNombreRol(rs.getString(5));
                el.setGenero(rs.getString(6));
                el.setEmail(rs.getString(7));
                el.setIdEstado(rs.getInt(8));
                el.setEstado(rs.getString(9));
                listaUsuarios.add(el);
            }
        } catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        //devolvemos el arreglo
        return listaUsuarios;
    }

    @Override
    public Object listarUno(Connection conexion, int id) {
        Usuario userDTO = new Usuario();
        try {
            String query = "SELECT nro_documento_user, nombre_user, fecha_nacimiento_user, id_rol, nombre_rol, genero, "
                    + "email, id_estado, nombre_estado "
                    + "FROM usuario "
                    + "INNER JOIN rol ON(usuario.id_rol=rol.idrol)"
                    + "INNER JOIN estado_usuarios ON(usuario.id_estado=estado_usuarios.Id) "
                    + "WHERE nro_documento_user=?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                userDTO.setId(rs.getString(1));
                userDTO.setUserName(rs.getString(2));
                userDTO.setFechaNacimiento(rs.getDate(3));
                userDTO.setIdRol(rs.getInt(4));
                userDTO.setNombreRol(rs.getString(5));
                userDTO.setGenero(rs.getString(6));
                userDTO.setEmail(rs.getString(7));
                userDTO.setIdEstado(rs.getInt(8));
            }
        } catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        //devolvemos el arreglo
        return userDTO;
    }

    @Override
    public String editarRegistro(Connection conexion, Object dto) {
        Usuario user = (Usuario) dto;
        java.sql.Date sqlDate = new java.sql.Date(user.getFechaNacimiento().getTime());
        int resultado = 0;
        String respuesta = "";
        String sql = "UPDATE usuario SET nombre_user=?, fecha_nacimiento_user=?, genero=?, id_estado=?, "
                    + "id_rol=?, email=? "
                + "WHERE nro_documento_user=?;";
        try {
            statement = conexion.prepareStatement(sql);
            statement.setString(1, user.getUserName());
            statement.setDate(2, sqlDate);
            statement.setString(3, user.getGenero());
            statement.setInt(4, user.getIdEstado());
            statement.setInt(5, user.getIdRol());
            statement.setString(6, user.getEmail());
            statement.setString(7, user.getId());

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

    @Override
    public String crearRegistro(Object dto, Connection conexion) {
        Usuario IngUsu = (Usuario) dto;
        java.sql.Date sqlDate = new java.sql.Date(IngUsu.getFechaNacimiento().getTime());
        String rta = "";
        try {
            statement = conexion.prepareStatement("INSERT INTO usuario(nro_documento_user, nombre_user, fecha_nacimiento_user, genero, id_estado, "
                    + "id_rol, email, password_user) VALUES (?,?,?,?,?,?,?,md5(?))");
            statement.setString(1, IngUsu.getId());
            statement.setString(2, IngUsu.getUserName());
            statement.setDate(3, sqlDate);
            statement.setString(4, IngUsu.getGenero());
            statement.setInt(5, IngUsu.getIdEstado());
            statement.setInt(6, IngUsu.getIdRol());
            statement.setString(7, IngUsu.getEmail());
            statement.setString(8, IngUsu.getPassword());
            int resultado = statement.executeUpdate();
            if (resultado == 0) {
                rta = "No se pudo registrar el usuario";//Mensaje No.6

            } else {
                rta = "Usuario Registrado";//Mensaje No.5
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
        Usuario userDTO = null;
        try {
            String query = "SELECT nro_documento_user, nombre_user, fecha_nacimiento_user, id_rol, password_user, genero"
                    + " FROM usuario "
                    + "WHERE nro_documento_user=? AND password_user=md5(?)";
            statement = conexion.prepareStatement(query);
            statement.setString(1, document);
            statement.setString(2, pass);
            rs = statement.executeQuery();
            while (rs.next()) {
                userDTO = new Usuario();
                userDTO.setId(rs.getString(1));
                userDTO.setUserName(rs.getString(2));
                userDTO.setFechaNacimiento(rs.getDate(3));
                userDTO.setIdRol(rs.getInt(4));
                userDTO.setPassword(rs.getString(5));
                userDTO.setGenero(rs.getString(6));
            }
        } catch (SQLException sqlexception) {
            return null;
        }
        return userDTO;
    }

}
