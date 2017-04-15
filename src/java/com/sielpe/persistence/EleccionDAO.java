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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                    + "AND ideleccion NOT IN (SELECT id_eleccion from votacion " +
"                                              WHERE id_user=?)";
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
