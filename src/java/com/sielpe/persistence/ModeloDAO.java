/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sielpe.persistence;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * interface con metodos usales en los DAO
 */
public interface ModeloDAO {

    public ArrayList<Object> listarTodo(Connection conexion);
    
    public Object listarUno(Connection conexion, int id);

    public String crearRegistro(Object dto, Connection conexion);
    
    public String editarRegistro(Connection conexion, Object dto, int id);
    
    public String cambiarEstado(String id, Connection conexion, int estado);

}
