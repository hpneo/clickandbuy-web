package com.clickandbuy.web.util;

import java.io.IOException;
import java.util.Enumeration;
import javax.faces.context.*;
import javax.servlet.http.*;

/**
 *
 * @author GUSTAVO
 */
public class WebUtil 
{
    
    
    public static Object getObjectSesion(String objectName) 
    {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getSession(false).getAttribute(objectName);
    }
     public static void setObjectSesion(String objectName, Object object)
     {
        //log.info("SE PUSO EN SESION EL OBJETO " + objectName + " DEL TIPO " + object.getClass());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession(false).setAttribute(objectName, object);
    }
     
    public static void deleteObjectSession(String objectName)
    {
         HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession(false).removeAttribute(Constantes.SESION_USUARIO);
        request.getSession(false).invalidate();	
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    } 
     
    public static HttpSession getSesion()
    {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }
    public static void sendRedirect(String ruta)
    {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	try 
        {
            
	    FacesContext.getCurrentInstance().responseComplete();
            //log.info(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/faces/" + ruta);
	    response.sendRedirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/faces/"+ruta);
	} 
        catch (IOException ioe) 
        {
	    //log.error(ioe.getMessage());
	}
    }
  
}
