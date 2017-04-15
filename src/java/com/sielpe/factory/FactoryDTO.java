package com.sielpe.factory;

import com.sielpe.model.*;
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
    public Candidato crearCandidato() {
        return new Candidato();
    }

    @Override
    public Eleccion crearEleccion() {
        return new Eleccion();
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
