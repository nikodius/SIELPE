package com.sielpe.factory;

import com.sielpe.model.*;
import java.sql.Time;
import java.util.Date;


/**
 * Interface factory
 */
public interface Factory {
    
    Usuario crearUsuario(String id, String genero, Date fecha, String userName, int idRol, String password, String email, int idEstado);
    Candidato crearCandidato(String id, String genero, Date fechaNacimiento, String nombre, int numeroLista, int idEleccion, byte[] bytesFoto); 
    Eleccion crearEleccion(String nombre, String descripcion, Date fechaInicioInscripcion, Date fechaFinInscripcion, Date fechaVotacion, Time horaInicioVotacion, Time horaFinVotacion); 
    Eleccion detallesEleccion(int id, String nombre, String descripcion, Date fechaInicioInscripcion, Date fechaFinInscripcion, Date fechaVotacion, Time horaInicioVotacion, Time horaFinVotacion);
    Rol crearRol(String user, String pass); 
    Voto crearVoto(int idEleccion, String idUser, String idCandidato, String estado); 
}
