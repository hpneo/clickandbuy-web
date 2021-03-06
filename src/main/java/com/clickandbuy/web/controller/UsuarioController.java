/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.controller;

import clickandbuy.upc.edu.core.business.UsuarioBusiness;
import clickandbuy.upc.edu.core.business.RolBusiness;
import clickandbuy.upc.edu.core.entity.Usuario;
import clickandbuy.upc.edu.core.entity.Rol;
import clickandbuy.upc.edu.core.exception.RolException;
import clickandbuy.upc.edu.core.exception.UsuarioException;
import com.clickandbuy.web.util.WebUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.*;
import javax.faces.model.SelectItem;

/**
 *
 * @author GUSTAVO
 */
@ManagedBean(name = "usuarioController")
@RequestScoped
public class UsuarioController {

    @ManagedProperty("#{param.id}")
    private int id = 0;
    private Usuario usuario = new Usuario();
    private Rol rol = new Rol();
    UsuarioBusiness usuarioBusiness = new UsuarioBusiness();

    public void insertar() throws UsuarioException {
        this.usuario.setRol(this.getRol());
        
        try {
            this.usuarioBusiness.addUsuario(this.usuario);
        } catch (UsuarioException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        WebUtil.sendRedirect("/usuarios");
    }

    public void actualizar() {
        if (this.usuario != null) {
            WebUtil.sendRedirect("/usuarios/" + this.id);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() throws UsuarioException {
        if (this.id != 0) {
            try {
                this.usuario = this.usuarioBusiness.getUsuarioByCode(this.id);
            } catch (UsuarioException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Usuario> getUsuarios() throws UsuarioException {
        try {
            return usuarioBusiness.listUsuario();
        } catch (UsuarioException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<Usuario>();
        }
    }

    public List<SelectItem> getRoles() throws RolException {
        List<SelectItem> roles = new ArrayList<SelectItem>();


        RolBusiness rolBusiness = new RolBusiness();
        List<Rol> rolesCollection;

        try {
            rolesCollection = rolBusiness.listRoles();
        } catch (RolException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            rolesCollection = new ArrayList<Rol>();
        }

        for (Rol r : rolesCollection) {
            roles.add(new SelectItem(r.getRolCodigo(), r.getRolNombre()));
        }

        return roles;
    }
}
