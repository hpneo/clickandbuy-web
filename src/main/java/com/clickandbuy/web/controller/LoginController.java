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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Garyfimo
 */
@ManagedBean(name = "loginController")
@ViewScoped
public class LoginController implements Serializable {

    private Usuario usuario = new Usuario();
    private UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
    private Cliente cliente = new Cliente();
    private ClienteBusiness clienteBusiness = new ClienteBusiness();

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getUsuarioNombre() {
        return usuario.getUsuNombreusuario();
    }

    public String getClienteNombre() {
        return cliente.getCliNombreusuario();
    }

    public void login() {
        try {
            if (usuarioBusiness.autenticarUsuario(usuario.getUsuNombreusuario(), usuario.getUsuContrasenia())) {
                usuario = usuarioBusiness.iniciarSesion(usuario.getUsuNombreusuario());
                FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                WebUtil.getSesion().setMaxInactiveInterval(Constantes.getSESIONMAX());
                WebUtil.setObjectSesion(Constantes.getSESIONUSUARIO(), usuario);
                
                WebUtil.sendRedirect("/bienvenida_usuario");
            } else {
                if (clienteBusiness.autenticarCliente(usuario.getUsuNombreusuario().toString(), usuario.getUsuContrasenia().toString())) {
                    cliente.setCliNombreusuario(usuario.getUsuNombreusuario());
                    cliente.setCliContrasenia(usuario.getUsuContrasenia());
                    cliente = clienteBusiness.iniciarSesion(cliente.getCliNombreusuario());
                    FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                    WebUtil.getSesion().setMaxInactiveInterval(Constantes.getSESIONMAX());
                    WebUtil.setObjectSesion(Constantes.getSESIONCLIENTE(), cliente);
                    
                    WebUtil.sendRedirect("/bienvenida_cliente");
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void logout() {
        WebUtil.deleteObjectSession(Constantes.getSESIONUSUARIO());
        WebUtil.deleteObjectSession(Constantes.getSESIONCLIENTE());

        WebUtil.sendRedirect("/login");
    }
}
