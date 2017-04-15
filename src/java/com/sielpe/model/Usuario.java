/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sielpe.model;

import java.util.Date;

/**
 *
 * @author NiKo
 */
public class Usuario extends Persona {
    
    private String userName;
    private int idRol;
    private String nombreRol;
    private String password;
    private String email;
    private int idEstado;
    private String estado;

    public Usuario(String id, String genero, Date fecha, String userName, int idRol, String password, String email, int idEstado) {
        super(id, genero, fecha);
        this.userName = userName;
        this.idRol = idRol;
        this.password = password;
        this.email = email;
        this.idEstado = idEstado;
    }

    public Usuario() {
    }
 
    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
  
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
