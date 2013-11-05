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
import static com.clickandbuy.web.util.WebUtil.getResponse;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Garyfimo
 */

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable 
{
    
    private Usuario usuario = new Usuario(); 
    private UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
    private Cliente cliente = new Cliente() ;
    private ClienteBusiness clienteBusiness = new ClienteBusiness();
    
    
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
    
    public LoginController()
    {
        usuarioBusiness = new UsuarioBusiness();
        clienteBusiness= new ClienteBusiness();
        if(usuario == null)
            usuario= new Usuario();
        if(cliente== null)
            cliente = new Cliente();
        
    }
    public void login(ActionEvent actionEvent) throws Exception
    {
        System.out.print("entro al login");
        try 
        {
            RequestContext context = RequestContext.getCurrentInstance(); 
            FacesMessage msg;
            boolean loggedIn;
            String ruta="";
            if(usuarioBusiness.autenticarUsuario(usuario.getUsuNombreusuario(), usuario.getUsuContrasenia()))
            {
                System.out.print("entro al if");
                usuario = usuarioBusiness.iniciarSesion(usuario.getUsuNombreusuario());
                loggedIn=true;
                msg= new FacesMessage(FacesMessage.SEVERITY_INFO,Constantes.MENSAJE_BIENVENIDA,usuario.getUsuNombreusuario());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(Constantes.SESION_USUARIO,usuario.getUsuNombreusuario());
                //WebUtil.setSession(Constantes.SESION_USUARIO, 1);
                System.out.print("apunto de redireccionar");
		System.out.println(WebUtil.getSession(Constantes.SESION_USUARIO));
                //WebUtil.redirect("/usuarios");       
                ruta= "http://localhost:8080/ClickandBuyWeb/faces/usuarios.xhtml";
                getResponse().sendRedirect(ruta);
            }
            else
                if(clienteBusiness.autenticarCliente(usuario.getUsuNombreusuario().toString(), usuario.getUsuContrasenia().toString()))
                {
                    System.out.print("entro al else");
                    cliente.setCliNombreusuario(usuario.getUsuNombreusuario());
                    cliente.setCliContrasenia(usuario.getUsuContrasenia());
                    loggedIn=true;
                    msg= new FacesMessage(FacesMessage.SEVERITY_INFO,Constantes.MENSAJE_BIENVENIDA,cliente.getCliNombreusuario());
                
                    WebUtil.setSession(Constantes.SESION_USUARIO, usuario);
                    WebUtil.redirect("/usuarios.xhtml");
                }
                else
                {
                    loggedIn=false;
                    msg= new FacesMessage(FacesMessage.SEVERITY_WARN,Constantes.MENSAJE_LOGEO_INCORRECTO,"Usuario o contraseña errada");
                
                    System.out.println("No ingresaste ni por cliente ni por usuario");
                }
            System.out.print(ruta);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            context.addCallbackParam("loggedIn", loggedIn);
            context.addCallbackParam("ruta", ruta);
        }
        catch (Exception ex) 
        {
            //Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
}
