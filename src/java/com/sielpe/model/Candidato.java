/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sielpe.model;

import java.awt.Image;
import java.util.Date;

/**
 *
 * @author NiKo
 */
public class Candidato extends Persona {
    
    private String nombre;
    private Image foto;
    private int numeroLista;
    private int idEleccion;
    private String nombreEleccion;
    private byte[] bytesFoto;

    public Candidato() {
    }

    public Candidato(String id, String genero, Date fechaNacimiento, String nombre, int numeroLista, int idEleccion, byte[] bytesFoto) {
        super(id, genero, fechaNacimiento);
        this.nombre = nombre;
        this.numeroLista = numeroLista;
        this.idEleccion = idEleccion;
        this.bytesFoto = bytesFoto;
    }
    
    public int getIdEleccion() {
        return idEleccion;
    }

    public void setIdEleccion(int idEleccion) {
        this.idEleccion = idEleccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public int getNumeroLista() {
        return numeroLista;
    }

    public void setNumeroLista(int numeroLista) {
        this.numeroLista = numeroLista;
    }

    public String getNombreEleccion() {
        return nombreEleccion;
    }

    public void setNombreEleccion(String nombreEleccion) {
        this.nombreEleccion = nombreEleccion;
    }

    public byte[] getBytesFoto() {
        return bytesFoto;
    }

    public void setBytesFoto(byte[] bytesFoto) {
        this.bytesFoto = bytesFoto;
    }

}
