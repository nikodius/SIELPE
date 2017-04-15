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
public abstract class Persona {
    private String id;
    private String genero;
    private Date fechaNacimiento;

    public Persona(String id, String genero, Date fechaNacimiento) {
        this.id = id;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Persona() {
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    
}
