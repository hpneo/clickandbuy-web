/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.controller;

import clickandbuy.upc.edu.core.business.CategoriaBusiness;
import clickandbuy.upc.edu.core.entity.Categoria;
import clickandbuy.upc.edu.core.exception.CategoriaException;
import com.clickandbuy.web.util.WebUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.*;

/**
 *
 * @author GUSTAVO
 */
@ManagedBean(name = "categoriaController")
@RequestScoped
public class CategoriaController {

    @ManagedProperty("#{param.id}")
    private int id = 0;
    private Categoria categoria = new Categoria();
    CategoriaBusiness categoriaBusiness = new CategoriaBusiness();

    public void insertar() throws Exception {
        try {
            this.categoriaBusiness.addCategoria(this.categoria);
        } catch (CategoriaException ex) {
            Logger.getLogger(CategoriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        WebUtil.sendRedirect("/categorias");
    }

    public void actualizar() {
        if (this.categoria != null) {
            WebUtil.sendRedirect("/categoria/" + this.id);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categoria getCategoria() throws Exception {
        if (this.id == 0) {
            this.categoria = new Categoria();
        } else {
            try {
                this.categoria = this.categoriaBusiness.getCategoriaByCode(this.id);
            } catch (CategoriaException ex) {
                this.categoria = new Categoria();
            }
        }

        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getCategorias() throws Exception {
        try {
            return categoriaBusiness.listCategoria();
        } catch (CategoriaException ex) {
            return new ArrayList<Categoria>();
        }
    }
}
