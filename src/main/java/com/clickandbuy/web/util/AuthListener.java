/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;

/**
 *
 * @author GUSTAVO
 */
public class AuthListener implements javax.faces.event.PhaseListener {

  @Override
  public void afterPhase(PhaseEvent event) {
    //
  }

  @Override
  public void beforePhase(PhaseEvent event) {
    System.out.println("===================================");
    System.out.println(WebUtil.getRequest().getServletPath());
    System.out.println("===================================");
    
    if (!WebUtil.getRequest().getServletPath().contains("login.xhtml")) {
      if (!AuthUtil.userSignedIn()) {
	WebUtil.redirect("/login.xhtml");
      }
    }
  }

  @Override
  public PhaseId getPhaseId() {
    return PhaseId.RENDER_RESPONSE;
  }
  
}
