/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sielpe.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author NiKo
 */
public class Conexion {
    
   private static Connection conexion = null;

    private static void conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/sielpe", "auto", "admin123");
        } catch (SQLException sqlException) {
           sqlException.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    //constructor
    private Conexion() {
    }
    //singleton
    public static Connection getInstance() {
        if (conexion == null) {
            conectar();
        }
        return conexion;
    }
}
