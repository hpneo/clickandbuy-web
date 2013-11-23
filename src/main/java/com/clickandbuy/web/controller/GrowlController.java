/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.controller;

import com.clickandbuy.web.util.Constantes;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Piero
 */
@ManagedBean(name = "growlController")
public class GrowlController {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void login(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(Constantes.getMENSAJE_LOGEO_CORRECTO(), Constantes.getMENSAJE_BIENVENIDA() + text));
    }
}
