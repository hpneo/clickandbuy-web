/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.controller;

import clickandbuy.upc.edu.core.business.ClienteBusiness;
import clickandbuy.upc.edu.core.business.UsuarioBusiness;
import clickandbuy.upc.edu.core.entity.Cliente;
import clickandbuy.upc.edu.core.entity.Usuario;
import com.clickandbuy.web.util.Constantes;
import com.clickandbuy.web.util.WebUtil;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Garyfimo
 */

@ManagedBean(name = "loginController")
@ViewScoped
public class LoginController
{
    
    private Usuario usuario = new Usuario();
    private UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
    private Cliente cliente = new Cliente();
    private ClienteBusiness clienteBusiness = new ClienteBusiness();
    public void login()
    {
        try 
        {
            if(usuarioBusiness.autenticarUsuario(usuario.getUsuNombreusuario(), usuario.getUsuContrasenia()))
            {
                
                usuario = usuarioBusiness.iniciarSesion(usuario.getUsuNombreusuario());
                System.out.println("Bienvenido usuario");
                FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                WebUtil.getSesion().setMaxInactiveInterval(Constantes.SESION_MAX);
                
                WebUtil.setObjectSession(Constantes.SESION_USUARIO, usuario);
                WebUtil.sendRedirect("/usuarios.xhtml");
            }
            else
                if(clienteBusiness.autenticarCliente(usuario.getUsuNombreusuario().toString(), usuario.getUsuContrasenia().toString()))
                {
                    cliente.setCliNombreusuario(usuario.getUsuNombreusuario());
                    cliente.setCliContrasenia(usuario.getUsuContrasenia());
                    System.out.println("Bienvenido cliente");
                    FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                    WebUtil.getSesion().setMaxInactiveInterval(Constantes.SESION_MAX);
                    WebUtil.setObjectSession(Constantes.SESION_CLIENTE, cliente);
                    WebUtil.sendRedirect("/productos.xhtml");
                }
                else
                System.out.println("No ingresaste por feo");
        }
        catch (Exception ex) 
        {
            //Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Usuario getUsuario() 
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario) 
    {
        this.usuario = usuario;
    }
    
}
