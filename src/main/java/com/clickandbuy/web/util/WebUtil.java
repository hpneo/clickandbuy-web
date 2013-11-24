package com.clickandbuy.web.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.*;
import javax.servlet.http.*;

/**
 *
 * @author GUSTAVO
 */
public class WebUtil {

    public static Object getObjectSesion(String objectName) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getSession(false).getAttribute(objectName);
    }

    public static void setObjectSesion(String objectName, Object object) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession(false).setAttribute(objectName, object);
    }

    public static void deleteObjectSession(String objectName) {
        setObjectSesion(objectName, null);
    }

    public static HttpSession getSesion() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public static void sendRedirect(String ruta) {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {

            FacesContext.getCurrentInstance().responseComplete();
            response.sendRedirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + ruta);
        } catch (IOException ioe) {
            Logger.getLogger(WebUtil.class.getName()).log(Level.SEVERE, null, ioe);
        }
    }

    private static FacesContext getInstance() {
        return FacesContext.getCurrentInstance();
    }

    private static ExternalContext getExternalContext() {
        return getInstance().getExternalContext();
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }
}
