/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sielpe.facade;

import com.sielpe.model.*;
import com.sielpe.persistence.*;
import com.sielpe.utility.Conexion;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nico
 */
public class FachadaDAO {
    UsuarioDAO udao;
    EleccionDAO edao;
    CandidatoDAO cdao;
    VotacionDAO vdao;
    Connection conexion;

    public FachadaDAO(){
        udao = new UsuarioDAO();
        edao = new EleccionDAO();
        cdao = new CandidatoDAO();
        vdao = new VotacionDAO();
        conexion = Conexion.getInstance();
    }

    /**
     * metodo para detalles de usuario en login
     * @param user
     * @return
     * @throws MiExcepcion 
     */
    public Usuario validateUser(String id, String pass){
        return udao.loginValidate(conexion, id, pass);
    }
    
    /**
     * metodo para listar elecciones
     * @return
     * @throws MiExcepcion 
     */
    public List<Usuario> listarUsuarios()  {
        List<Object> dto = udao.listarTodo(conexion);
        List<Usuario> usuarios = new ArrayList();
        for (Object mDTO : dto) {
            usuarios.add((Usuario) mDTO);
        }
        return usuarios;
    }
    
    /**
     * metodo para listar elecciones
     * @return
     * @throws MiExcepcion 
     */
    public List<Eleccion> listarElecciones(String id)  {
        List<Object> dto = edao.listarTodo(conexion, id);
        List<Eleccion> elecciones = new ArrayList();
        for (Object mDTO : dto) {
            elecciones.add((Eleccion) mDTO);
        }
        return elecciones;
    }
    
    /**
     * metodo para listar elecciones
     * @return
     * @throws MiExcepcion 
     */
    public List<Candidato> listarCandidatosEleccion(int id)  {
        List<Object> dto = cdao.listarTodo(conexion, id);
        List<Candidato> candidatos = new ArrayList();
        for (Object mDTO : dto) {
            candidatos.add((Candidato) mDTO);
        }
        return candidatos;
    }
    
    /**
     * metodo para insertar votos
     * @return
     * @throws MiExcepcion 
     */
    public String insertarVoto(Voto vdto){
        return vdao.crearRegistro(vdto, conexion);
    }
    
    /**
     * metodo para insertar usuario
     * @param urdto
     * @return
     * @throws MiExcepcion 
     */
    public synchronized String insertarUsuario(Usuario urdto) {
        return udao.crearRegistro(urdto, conexion);
    }
    

}
