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
public class AuthorizationListener implements PhaseListener {

    @Override
    public void beforePhase(PhaseEvent event) {
        // Required method by implementing an interface
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        if (!WebUtil.getRequest().getServletPath().contains("login")) {
            System.out.println("user_signed_in: " + AuthUtil.userSignedIn());
            System.out.println("cliente_signed_in: " + AuthUtil.clienteSignedIn());
            if (!AuthUtil.userSignedIn() && !AuthUtil.clienteSignedIn()) {
                WebUtil.sendRedirect("/login");
            }
        }
    }
}
