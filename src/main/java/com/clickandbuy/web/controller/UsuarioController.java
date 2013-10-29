/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.controller;

import clickandbuy.upc.edu.core.business.UsuarioBusiness;
import clickandbuy.upc.edu.core.business.RolBusiness;
import clickandbuy.upc.edu.core.entity.Usuario;
import clickandbuy.upc.edu.core.entity.Rol;
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
  private List<Usuario> usuarios = new ArrayList<Usuario>();
  private List<SelectItem> roles = new ArrayList<SelectItem>();
  UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
  
  public void insertar() {
    System.out.println("========================");
    System.out.println(this.usuario.getUsuCodigo());
    System.out.println(this.usuario.getUsuNombreusuario());
    System.out.println("========================");
    try {
      this.usuarioBusiness.addUsuario(this.usuario);
    } catch (Exception ex) {
      Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    WebUtil.redirect("/usuarios");
  }
  
  public void actualizar() {
    if (this.usuario != null) {
      System.out.println("========================");
      System.out.println(this.usuario.getUsuCodigo());
      System.out.println(this.usuario.getUsuNombreusuario());
      System.out.println("========================");
      
      WebUtil.redirect("/usuarios/" + this.id);
    }
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Usuario getUsuario() {
    if (this.id == 0) {
      this.usuario = new Usuario();
    }
    else {
      try {
	// this.usuario = this.usuarioBusiness.getUsuarioByCode(this.id);
	this.usuario = new Usuario();
      } catch (Exception ex) {
	this.usuario = new Usuario();
      }
    }
    
    return this.usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
  
  public List<Usuario> getUsuarios() 
  {
    try 
    {
       return usuarioBusiness.listUsuario();
    } catch (Exception ex) {
      return new ArrayList<Usuario>();
    }
  }

  public List<SelectItem> getRoles() 
  {
    this.roles = new ArrayList<SelectItem>();
    
    RolBusiness rolBusiness = new RolBusiness();
    List<Rol> _roles;
    
    try 
    {
      // _roles = rolBusiness.listRoles();
      _roles = new ArrayList<Rol>();
    } catch (Exception ex) {
      _roles = new ArrayList<Rol>();
    }
    
    for (Rol r:_roles) {
      roles.add(new SelectItem(r.getRolCodigo(), r.getRolNombre()));
    }
    
    return roles;
  }
}
