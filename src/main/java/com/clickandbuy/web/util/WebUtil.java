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
    public static Object getSession(String name) 
    {
        return ((HttpSession) getRequest().getSession(true)).getAttribute(name);
    }
    public static void setSession(String name, Object value) 
    {
        ((HttpSession) getRequest().getSession(true)).setAttribute(name, value);
    }
    public static void redirect(String path) 
    {
        try 
        {
            getInstance().responseComplete();
            getResponse().sendRedirect(getExternalContext().getRequestContextPath() + path);
        } 
        catch (Exception e) 
        {
        }
    }
  
    public static HttpServletRequest getRequest() 
    {
        return (HttpServletRequest) getExternalContext().getRequest();
    }
  
    public static HttpServletResponse getResponse() 
    {
        return (HttpServletResponse) getExternalContext().getResponse();
    }
  
    private static FacesContext getInstance() 
    {
        return FacesContext.getCurrentInstance();
    }
    private static ExternalContext getExternalContext() 
    {
        return getInstance().getExternalContext();
    }
  
}
