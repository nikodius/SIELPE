/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sielpe.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 *
 * @author NiKo
 */
public class Eleccion {
    private int id;
    private String nombre;
    private String descripcion;
    private Date fechaInicioInscripcion;
    private Date fechaFinInscripcion;
    private Date fechaVotacion;
    private Time horaInicioVotacion;
    private Time horaFinVotacion;
    private List<Candidato> listaCandidatos;

    public Eleccion() {
    }

    public Eleccion(String nombre, String descripcion, Date fechaInicioInscripcion, Date fechaFinInscripcion, Date fechaVotacion, Time horaInicioVotacion, Time horaFinVotacion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicioInscripcion = fechaInicioInscripcion;
        this.fechaFinInscripcion = fechaFinInscripcion;
        this.fechaVotacion = fechaVotacion;
        this.horaInicioVotacion = horaInicioVotacion;
        this.horaFinVotacion = horaFinVotacion;
    }
    
    public Eleccion(int id, String nombre, String descripcion, Date fechaInicioInscripcion, Date fechaFinInscripcion, Date fechaVotacion, Time horaInicioVotacion, Time horaFinVotacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicioInscripcion = fechaInicioInscripcion;
        this.fechaFinInscripcion = fechaFinInscripcion;
        this.fechaVotacion = fechaVotacion;
        this.horaInicioVotacion = horaInicioVotacion;
        this.horaFinVotacion = horaFinVotacion;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    

    public Date getFechaInicioInscripcion() {
        return fechaInicioInscripcion;
    }

    public void setFechaInicioInscripcion(Date fechaInicioInscripcion) {
        this.fechaInicioInscripcion = fechaInicioInscripcion;
    }

    public Date getFechaFinInscripcion() {
        return fechaFinInscripcion;
    }

    public void setFechaFinInscripcion(Date fechaFinInscripcion) {
        this.fechaFinInscripcion = fechaFinInscripcion;
    }

    public Date getFechaVotacion() {
        return fechaVotacion;
    }

    public void setFechaVotacion(Date fechaVotacion) {
        this.fechaVotacion = fechaVotacion;
    }

    public Time getHoraInicioVotacion() {
        return horaInicioVotacion;
    }

    public void setHoraInicioVotacion(Time horaInicioVotacion) {
        this.horaInicioVotacion = horaInicioVotacion;
    }

    public Time getHoraFinVotacion() {
        return horaFinVotacion;
    }

    public void setHoraFinVotacion(Time horaFinVotacion) {
        this.horaFinVotacion = horaFinVotacion;
    }

    

    public List<Candidato> getListaCandidatos() {
        return listaCandidatos;
    }

    public void setListaCandidatos(List<Candidato> listaCandidatos) {
        this.listaCandidatos = listaCandidatos;
    }
    
    
}
