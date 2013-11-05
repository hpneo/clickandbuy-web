/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Piero
 */
public class AuthorizationListener implements PhaseListener
{
    public void afterPhase(PhaseEvent event) 
    {
        FacesContext facesContext = event.getFacesContext();
        String currentPage = facesContext.getViewRoot().getViewId();
        boolean isLoginPage = (currentPage.lastIndexOf("login.xhtml") > -1) ? true: false;
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        if(!isLoginPage && session==null)
        {
            NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
            nh.handleNavigation(facesContext, null,"/login.xhtml");
        }
        else
        {
            Object currentUser = session.getAttribute(Constantes.SESION_USUARIO);
            Object currentUser2 = session.getAttribute(Constantes.SESION_CLIENTE);
            if (!isLoginPage && (currentUser == null || currentUser == "")  && (currentUser2 == null || currentUser2 == "")) 
            {
                NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
                nh.handleNavigation(facesContext, null,"/login.xhtml");
            }
        }
    }
 
    public void beforePhase(PhaseEvent event) 
    {
      /*if (!WebUtil.getRequest().getServletPath().contains("login.xhtml"))
      {
        if (!AuthorizationUtil.userSignedIn()) 
        {
            WebUtil.redirect("/login.xhtml");
        }
      }*/
    }
    public PhaseId getPhaseId() 
    {
        return PhaseId.RESTORE_VIEW;
    }
}
