/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.controller;
import com.clickandbuy.web.util.Constantes;
import com.clickandbuy.web.util.WebUtil;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

/**
 *
 * @author Piero
 */
@ManagedBean(name = "menuController")
@ViewScoped
public class MenuController implements Serializable 
{
     private MenuModel menuOpciones = new DefaultMenuModel();
    

    public MenuController() 
    {
        System.out.println("se entro al envento");
        if(WebUtil.getObjectSesion(Constantes.SESION_USUARIO)!=null && WebUtil.getObjectSesion(Constantes.SESION_CLIENTE)==null)
        {
            System.out.println("se entro al primer if");
            
            Submenu submenu7 = new Submenu();
            submenu7.setLabel(Constantes.MENU_ITEM_LOGO);
            submenu7.setIcon(Constantes.MENU_ITEM_URLLOGO);
            //submenu.(Constantes.MENU_ITEM_URLCATEGORIAS);
            menuOpciones.addSubmenu(submenu7);
            
            
            
            Submenu submenu = new Submenu();
            submenu.setLabel(Constantes.MENU_ITEM_CATEGORIAS);
            //submenu.(Constantes.MENU_ITEM_URLCATEGORIAS);
            menuOpciones.addSubmenu(submenu);
            
            Submenu submenu2 = new Submenu();
            submenu2.setLabel(Constantes.MENU_ITEM_USUARIOS);
            //submenu2.setUrl(Constantes.MENU_ITEM_URLUSUARIOS);
            menuOpciones.addSubmenu(submenu2);
            
            Submenu submenu3 = new Submenu();
            submenu3.setLabel(Constantes.MENU_ITEM_PRODUCTOS);
            //item3.setUrl(Constantes.MENU_ITEM_URLPRODUCTOS);
            menuOpciones.addSubmenu(submenu3);
            
            Submenu submenu4 = new Submenu();
            submenu4.setLabel(Constantes.MENU_ITEM_COTIZACIONES);
            //submenu4.setUrl(Constantes.MENU_ITEM_URLCOTIZACIONES);
            menuOpciones.addSubmenu(submenu4);
            
            Submenu submenu5 = new Submenu();
            submenu5.setLabel(Constantes.MENU_ITEM_PEDIDOS);
            //submenu5.setUrl(Constantes.MENU_ITEM_URLPEDIDOS);
            menuOpciones.addSubmenu(submenu5);
            
            Submenu submenu6 = new Submenu();
            submenu6.setLabel(Constantes.MENU_ITEM_EXIT);
            //submenu.(Constantes.MENU_ITEM_URLCATEGORIAS);
            menuOpciones.addSubmenu(submenu6);
        }
        else
        {
            if(WebUtil.getObjectSesion(Constantes.SESION_USUARIO)==null && WebUtil.getObjectSesion(Constantes.SESION_CLIENTE)!=null)
            {
                System.out.println("se entro al segundo if");
                
                Submenu submenu7 = new Submenu();
                submenu7.setLabel(Constantes.MENU_ITEM_LOGO);
                //submenu.(Constantes.MENU_ITEM_URLCATEGORIAS);
                menuOpciones.addSubmenu(submenu7);
                
                Submenu submenu4 = new Submenu();
                submenu4.setLabel(Constantes.MENU_ITEM_COTIZACIONES);
                //submenu4.setUrl(Constantes.MENU_ITEM_URLCOTIZACIONES);
                menuOpciones.addSubmenu(submenu4);

                Submenu submenu5 = new Submenu();
                submenu5.setLabel(Constantes.MENU_ITEM_PEDIDOS);
                //submenu5.setUrl(Constantes.MENU_ITEM_URLPEDIDOS);
                menuOpciones.addSubmenu(submenu5);
                
                Submenu submenu6 = new Submenu();
                submenu6.setLabel(Constantes.MENU_ITEM_EXIT);
                //submenu.(Constantes.MENU_ITEM_URLCATEGORIAS);
                menuOpciones.addSubmenu(submenu6);
            }
        }
       

    }

    public MenuModel getMenuOpciones() {
        return menuOpciones;
    }
}   

