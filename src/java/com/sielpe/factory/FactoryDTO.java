package com.sielpe.factory;

import com.sielpe.model.*;
import java.sql.Time;
import java.util.Date;

/**
 * Implementacion factory DTO
 * usada por controladores, se encarga de crear objetos tipo DTO
 *
 */
public class FactoryDTO implements Factory {

    @Override
    public Usuario crearUsuario(String id, String genero, Date fecha, String userName, int idRol, String password, String email, int idEstado) {
        return new Usuario(id, genero, fecha, userName, idRol, password, email, idEstado);
    }

    @Override
    public Candidato crearCandidato(String id, String genero, Date fechaNacimiento, String nombre, int numeroLista, int idEleccion, byte[] bytesFoto) {
        return new Candidato(id, genero, fechaNacimiento, nombre, numeroLista, idEleccion, bytesFoto);
    }

    @Override
    public Eleccion crearEleccion(String nombre, String descripcion, Date fechaInicioInscripcion, Date fechaFinInscripcion, Date fechaVotacion, Time horaInicioVotacion, Time horaFinVotacion) {
        return new Eleccion(nombre, descripcion, fechaInicioInscripcion, fechaFinInscripcion, fechaVotacion, horaInicioVotacion, horaFinVotacion);
    }
    
    @Override
    public Eleccion detallesEleccion(int id, String nombre, String descripcion, Date fechaInicioInscripcion, Date fechaFinInscripcion, Date fechaVotacion, Time horaInicioVotacion, Time horaFinVotacion) {
        return new Eleccion(id, nombre, descripcion, fechaInicioInscripcion, fechaFinInscripcion, fechaVotacion, horaInicioVotacion, horaFinVotacion);
    }
    

    @Override
    public Rol crearRol(String user, String pass) {
        return new Rol();
    }

    @Override
    public Voto crearVoto(int idEleccion, String idUser, String idCandidato, String estado) {
        return new Voto(idEleccion, idUser, idCandidato, estado);
    }

    
}
