package com.sielpe.factory;

import com.sielpe.model.*;
import java.util.Date;


/**
 * Interface factory
 */
public interface Factory {
    
    Usuario crearUsuario(String id, String genero, Date fecha, String userName, int idRol, String password, String email, int idEstado);
    Candidato crearCandidato(); 
    Eleccion crearEleccion(); 
    Rol crearRol(String user, String pass); 
    Voto crearVoto(int idEleccion, String idUser, String idCandidato, String estado); 
}
