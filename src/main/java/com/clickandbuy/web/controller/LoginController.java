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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Garyfimo
 */

@ManagedBean(name = "loginController")
@ViewScoped
public class LoginController implements Serializable 
{
    
    private Usuario usuario = new Usuario(); 
    private UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
    private Cliente cliente = new Cliente() ;
    private ClienteBusiness clienteBusiness = new ClienteBusiness();
    private boolean logeado = false;
    
    public Cliente getCliente() 
    {
        return cliente;
    }
    public void setCliente(Cliente cliente) 
    {
        this.cliente = cliente;
    }
    public Usuario getUsuario() 
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario) 
    {
        this.usuario = usuario;
    }
    public String getUsuario_nombreusuario()
    {
        return usuario.getUsuNombreusuario();
    }
    public String getCliente_nombreusuario()
    {
        return cliente.getCliNombreusuario();
    }
    public void login() 
    {
        try 
        {
            if(usuarioBusiness.autenticarUsuario(usuario.getUsuNombreusuario(), usuario.getUsuContrasenia()))
            {
                System.out.println("Entro al if");
                System.out.println(usuario.getUsuCodigo());
                System.out.println(usuario.getUsuNombreusuario());
                usuario = usuarioBusiness.iniciarSesion(usuario.getUsuNombreusuario());
                FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                WebUtil.getSesion().setMaxInactiveInterval(Constantes.SESION_MAX);
                //msg= new FacesMessage(FacesMessage.SEVERITY_INFO,Constantes.MENSAJE_BIENVENIDA,usuario.getUsuNombreusuario());
                WebUtil.setObjectSesion(Constantes.SESION_USUARIO, usuario);
                logeado= true;
                System.out.print(WebUtil.getObjectSesion(Constantes.SESION_USUARIO));
                WebUtil.sendRedirect("/bienvenida_usuario");   
            }
            else
            {
                if(clienteBusiness.autenticarCliente(usuario.getUsuNombreusuario().toString(), usuario.getUsuContrasenia().toString()))
                {
                    System.out.print("entro al else");
                    cliente.setCliNombreusuario(usuario.getUsuNombreusuario());
                    cliente.setCliContrasenia(usuario.getUsuContrasenia());
                    cliente = clienteBusiness.iniciarSesion(cliente.getCliNombreusuario());
                    FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                    WebUtil.getSesion().setMaxInactiveInterval(Constantes.SESION_MAX);
                    //msg= new FacesMessage(FacesMessage.SEVERITY_INFO,Constantes.MENSAJE_BIENVENIDA,cliente.getCliNombreusuario());
                    WebUtil.setObjectSesion(Constantes.SESION_CLIENTE, cliente);
                    logeado= true;
                    System.out.print(WebUtil.getObjectSesion(Constantes.SESION_CLIENTE));
                    WebUtil.sendRedirect("/bienvenida_cliente"); 
                }
                else
                {
                    //msg= new FacesMessage(FacesMessage.SEVERITY_WARN,Constantes.MENSAJE_LOGEO_INCORRECTO,"Usuario o contraseña errada");
                    logeado=false;
                    System.out.println("No ingresaste ni por cliente ni por usuario");
                }
            }
           
        }
        catch (Exception ex) 
        {
            //Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void logout()
    {
        if(WebUtil.getObjectSesion(Constantes.SESION_USUARIO)!=null)
            WebUtil.deleteObjectSession(Constantes.SESION_USUARIO);
        else
            if(WebUtil.getObjectSesion(Constantes.SESION_CLIENTE)!=null)
                WebUtil.deleteObjectSession(Constantes.SESION_CLIENTE);
        WebUtil.sendRedirect("/login"); 
    }
    
    
}
