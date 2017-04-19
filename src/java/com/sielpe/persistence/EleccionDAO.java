/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sielpe.persistence;

import com.sielpe.model.Eleccion;
import com.sielpe.model.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * clase modelo dao usuario
 */
public class EleccionDAO implements ModeloDAO {

    PreparedStatement statement;
    ResultSet rs;

    @Override
    public ArrayList<Object> listarTodo(Connection conexion) {
        ArrayList<Object> listaElecciones = new ArrayList();
        try {
            String query = "SELECT ideleccion, nombre_eleccion, descripcion, fecha_inicio_inscripcion, fecha_fin_inscripcion, "
                    + "fecha_votacion, hora_inicio_votacion, hora_fin_votacion  "
                    + "FROM eleccion";
            statement = conexion.prepareStatement(query);
            rs = statement.executeQuery();
            while (rs.next()) {
                Eleccion el = new Eleccion();
                el.setId(rs.getInt(1));
                el.setNombre(rs.getString(2));
                el.setDescripcion(rs.getString(3));
                el.setFechaInicioInscripcion(rs.getDate(4));
                el.setFechaFinInscripcion(rs.getDate(5));
                el.setFechaVotacion(rs.getDate(6));
                el.setHoraInicioVotacion(rs.getTime(7));
                el.setHoraFinVotacion(rs.getTime(8));
                listaElecciones.add(el);
            }
        } catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        //devolvemos el arreglo
        return listaElecciones;
    }
    
    public ArrayList<Object> listarVigentes(Connection conexion) {
        ArrayList<Object> listaElecciones = new ArrayList();
        try {
            String query = "SELECT ideleccion, nombre_eleccion "
                    + "FROM eleccion "
                    + "WHERE NOW() BETWEEN fecha_inicio_inscripcion AND fecha_fin_inscripcion";
            statement = conexion.prepareStatement(query);
            rs = statement.executeQuery();
            while (rs.next()) {
                Eleccion el = new Eleccion();
                el.setId(rs.getInt(1));
                el.setNombre(rs.getString(2));
                listaElecciones.add(el);
            }
        } catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        //devolvemos el arreglo
        return listaElecciones;
    }

    @Override
    public Object listarUno(Connection conexion, int id) {
        Eleccion eleccionDTO = new Eleccion();
        try {
            String query = "SELECT ideleccion, nombre_eleccion, descripcion, fecha_inicio_inscripcion, fecha_fin_inscripcion, "
                    + "fecha_votacion, hora_inicio_votacion, hora_fin_votacion "
                    + "FROM eleccion "
                    + "WHERE ideleccion=?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                eleccionDTO.setId(rs.getInt(1));
                eleccionDTO.setNombre(rs.getString(2));
                eleccionDTO.setDescripcion(rs.getString(3));
                eleccionDTO.setFechaInicioInscripcion(rs.getDate(4));
                eleccionDTO.setFechaFinInscripcion(rs.getDate(5));
                eleccionDTO.setFechaVotacion(rs.getDate(6));
                eleccionDTO.setHoraInicioVotacion(rs.getTime(7));
                eleccionDTO.setHoraFinVotacion(rs.getTime(8));
            }
        } catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        //devolvemos el arreglo
        return eleccionDTO;
    }

    @Override
    public String editarRegistro(Connection conexion, Object dto) {
        Eleccion elec = (Eleccion) dto;
        java.sql.Date fechaInicio = new java.sql.Date(elec.getFechaInicioInscripcion().getTime());
        java.sql.Date fechaFin = new java.sql.Date(elec.getFechaFinInscripcion().getTime());
        java.sql.Date fechaVotacion = new java.sql.Date(elec.getFechaVotacion().getTime());
        int resultado = 0;
        String respuesta = "";
        String sql = "UPDATE eleccion SET nombre_eleccion=?, descripcion=?, fecha_inicio_inscripcion=?, fecha_fin_inscripcion=?, "
                + "fecha_votacion=?, hora_inicio_votacion=?, hora_fin_votacion=? "
                + "WHERE idEleccion=?;";
        try {
            statement = conexion.prepareStatement(sql);
            statement.setString(1, elec.getNombre());
            statement.setString(2, elec.getDescripcion());
            statement.setDate(3, fechaInicio);
            statement.setDate(4, fechaFin);
            statement.setDate(5, fechaVotacion);
            statement.setTime(6, elec.getHoraInicioVotacion());
            statement.setTime(7, elec.getHoraFinVotacion());
            statement.setInt(8, elec.getId());

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
        Eleccion eleccion = (Eleccion) dto;
        java.sql.Date fechaIncioIns = new java.sql.Date(eleccion.getFechaInicioInscripcion().getTime());
        java.sql.Date fechaFinIns = new java.sql.Date(eleccion.getFechaFinInscripcion().getTime());
        java.sql.Date fechaVotacion = new java.sql.Date(eleccion.getFechaVotacion().getTime());
        String rta = "";
        try {
            String sql = "INSERT INTO eleccion(nombre_eleccion, descripcion, fecha_inicio_inscripcion, fecha_fin_inscripcion, fecha_votacion, hora_inicio_votacion, hora_fin_votacion) "
                    + "VALUES (?,?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, eleccion.getNombre());
            statement.setString(2, eleccion.getDescripcion());
            statement.setDate(3, fechaIncioIns);
            statement.setDate(4, fechaFinIns);
            statement.setDate(5, fechaVotacion);
            statement.setTime(6, eleccion.getHoraInicioVotacion());
            statement.setTime(7, eleccion.getHoraFinVotacion());
            int resultado = statement.executeUpdate();
            if (resultado == 0) {
                rta = "No se pudo registrar la eleccion";//Mensaje No.6

            } else {
                rta = "Eleccion Registrada";//Mensaje No.5
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

    public ArrayList<Object> listarTodo(Connection conexion, String id) {
        ArrayList<Object> listaElecciones = new ArrayList();
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();
        try {
            String query = "SELECT ideleccion, nombre_eleccion, descripcion, fecha_inicio_inscripcion, fecha_fin_inscripcion, "
                    + "fecha_votacion, hora_inicio_votacion, hora_fin_votacion  "
                    + "FROM eleccion "
                    + "WHERE fecha_votacion=? AND (hora_inicio_votacion <= ? AND hora_fin_votacion >= ?)"
                    + "AND ideleccion NOT IN (SELECT id_eleccion from votacion "
                    + "                                              WHERE id_user=?)";
            statement = conexion.prepareStatement(query);
            statement.setDate(1, Date.valueOf(fechaActual));
            statement.setTime(2, Time.valueOf(horaActual));
            statement.setTime(3, Time.valueOf(horaActual));
            statement.setString(4, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                Eleccion el = new Eleccion();
                el.setId(rs.getInt(1));
                el.setNombre(rs.getString(2));
                el.setDescripcion(rs.getString(3));
                el.setFechaInicioInscripcion(rs.getDate(4));
                el.setFechaFinInscripcion(rs.getDate(5));
                el.setFechaVotacion(rs.getDate(6));
                el.setHoraInicioVotacion(rs.getTime(7));
                el.setHoraFinVotacion(rs.getTime(8));
                listaElecciones.add(el);
            }
        } catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        //devolvemos el arreglo
        return listaElecciones;
    }
}
