/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.controller;

import com.clickandbuy.web.util.Constantes;
import com.clickandbuy.web.util.WebUtil;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

/**
 *
 * @author Piero
 */
@ManagedBean(name = "menuController")
@ViewScoped
public class MenuController implements Serializable {

    private MenuModel menuOpciones = new DefaultMenuModel();

    public MenuController() {
        if (WebUtil.getObjectSesion(Constantes.getSESION_USUARIO()) != null && WebUtil.getObjectSesion(Constantes.getSESION_CLIENTE()) == null) {
            Submenu submenu7 = new Submenu();
            submenu7.setLabel(Constantes.getMENU_ITEM_LOGO());
            submenu7.setIcon(Constantes.getMENU_ITEM_URLLOGO());
            //submenu.(Constantes.MENU_ITEM_URLCATEGORIAS);
            menuOpciones.addSubmenu(submenu7);

            Submenu submenu = new Submenu();
            submenu.setLabel(Constantes.getMENU_ITEM_CATEGORIAS());
            //submenu.(Constantes.MENU_ITEM_URLCATEGORIAS);
            menuOpciones.addSubmenu(submenu);

            Submenu submenu2 = new Submenu();
            submenu2.setLabel(Constantes.getMENU_ITEM_USUARIOS());
            //submenu2.setUrl(Constantes.MENU_ITEM_URLUSUARIOS);
            menuOpciones.addSubmenu(submenu2);

            Submenu submenu3 = new Submenu();
            submenu3.setLabel(Constantes.getMENU_ITEM_PRODUCTOS());
            //item3.setUrl(Constantes.MENU_ITEM_URLPRODUCTOS);
            menuOpciones.addSubmenu(submenu3);

            Submenu submenu4 = new Submenu();
            submenu4.setLabel(Constantes.getMENU_ITEM_COTIZACIONES());
            menuOpciones.addSubmenu(submenu4);

            Submenu submenu5 = new Submenu();
            submenu5.setLabel(Constantes.getMENU_ITEM_PEDIDOS());
            menuOpciones.addSubmenu(submenu5);

            Submenu submenu6 = new Submenu();
            submenu6.setLabel(Constantes.getMENU_ITEM_EXIT());
            menuOpciones.addSubmenu(submenu6);
        } else {
            if (WebUtil.getObjectSesion(Constantes.getSESION_USUARIO()) == null && WebUtil.getObjectSesion(Constantes.getSESION_CLIENTE()) != null) {
                Submenu submenu7 = new Submenu();
                submenu7.setLabel(Constantes.getMENU_ITEM_LOGO());
                menuOpciones.addSubmenu(submenu7);

                Submenu submenu4 = new Submenu();
                submenu4.setLabel(Constantes.getMENU_ITEM_COTIZACIONES());
                menuOpciones.addSubmenu(submenu4);

                Submenu submenu5 = new Submenu();
                submenu5.setLabel(Constantes.getMENU_ITEM_PEDIDOS());
                menuOpciones.addSubmenu(submenu5);

                Submenu submenu6 = new Submenu();
                submenu6.setLabel(Constantes.getMENU_ITEM_EXIT());
                menuOpciones.addSubmenu(submenu6);
            }
        }


    }

    public MenuModel getMenuOpciones() {
        return menuOpciones;
    }
}
