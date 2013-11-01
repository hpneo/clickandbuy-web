package com.clickandbuy.web.util;

import java.io.IOException;
import javax.faces.context.*;
import javax.servlet.http.*;

/**
 *
 * @author GUSTAVO
 */
public class WebUtil 
{
    
    public static HttpSession getSesion()
    {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }
    
    private static FacesContext getInstance() 
    {
        return FacesContext.getCurrentInstance();
    }
    public static Object getObjjectSession(String objectName) 
    {
        HttpServletRequest request = (HttpServletRequest) getInstance().getExternalContext().getRequest();
        return request.getSession(false).getAttribute(objectName);
    }
    public static void setObjectSession(String objectName, Object object) 
    {
        HttpServletRequest request = (HttpServletRequest) getInstance().getExternalContext().getRequest();
        request.getSession(false).setAttribute(objectName, object);
    }
    public static void setSession(String name, Object value) 
    {
        ((HttpSession) getRequest().getSession(true)).setAttribute(name, value);
    }
  
    public static void sendRedirect(String ruta) 
    {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	try 
        {
	    FacesContext.getCurrentInstance().responseComplete();
            response.sendRedirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/faces/" + ruta);
	} 
        catch (IOException ioe) 
        {
        }
    }
  
  public static HttpServletRequest getRequest() {
    return (HttpServletRequest) getExternalContext().getRequest();
  }
  
  public static HttpServletResponse getResponse() {
    return (HttpServletResponse) getExternalContext().getResponse();
  }
  
  
  
  private static ExternalContext getExternalContext() {
    return getInstance().getExternalContext();
  }
  
}
