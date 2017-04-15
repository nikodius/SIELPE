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
public class Voto {
    private String estado;
    private int idEleccion;
    private String idCandidato;
    private String idUsuario;

    public Voto(int idEleccion, String idUsuario, String idCandidato, String estado) {
        this.idEleccion = idEleccion;
        this.idUsuario = idUsuario;
        this.idCandidato = idCandidato;
        this.estado = estado;
    }

    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdEleccion() {
        return idEleccion;
    }

    public void setIdEleccion(int idEleccion) {
        this.idEleccion = idEleccion;
    }

    public String getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(String idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

        
    
}
