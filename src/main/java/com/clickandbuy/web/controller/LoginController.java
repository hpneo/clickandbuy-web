/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.controller;

import clickandbuy.upc.edu.core.business.UsuarioBusiness;
import clickandbuy.upc.edu.core.entity.Usuario;
import com.clickandbuy.web.util.WebUtil;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Garyfimo
 */

@ManagedBean(name = "loginController")
@ViewScoped
public class LoginController {
    
    private Usuario usuario = new Usuario();
    private UsuarioBusiness usuarioBusiness = new UsuarioBusiness();

    public void login()
    {
        try {
            if(usuarioBusiness.autenticarUsuario(usuario.getUsuNombreusuario(), usuario.getUsuContrasenia()))
            {
                System.out.println("INGRESASTE PAPI");
                WebUtil.setSession("current_user", 1);
		System.out.println(WebUtil.getSession("current_user"));
                WebUtil.redirect("/productos");
            }else
                System.out.println("NO INGRESASTE PENDEJO");
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
