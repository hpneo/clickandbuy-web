/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.controller;

import clickandbuy.upc.edu.core.business.CategoriaBusiness;
import clickandbuy.upc.edu.core.business.ProductoBusiness;
import clickandbuy.upc.edu.core.entity.Categoria;
import clickandbuy.upc.edu.core.entity.Producto;
import clickandbuy.upc.edu.core.exception.CategoriaException;
import clickandbuy.upc.edu.core.exception.ProductoException;
import com.clickandbuy.web.util.WebUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author GUSTAVO
 */
@ManagedBean(name = "productoController")
@RequestScoped
public class ProductoController {

    @ManagedProperty("#{param.id}")
    private int id = 0;
    private Producto producto = new Producto();
    private Categoria categoria = new Categoria();
    private ProductoBusiness productoBusiness = new ProductoBusiness();

    public void insertar() throws ProductoException {
        this.producto.setCategoria(this.getCategoria());

        try {
            this.productoBusiness.addProducto(this.producto);
        } catch (ProductoException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        WebUtil.sendRedirect("/productos");
    }

    public void actualizar() {
        if (this.producto != null) {
            WebUtil.sendRedirect("/productos/" + this.id);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producto getProducto() throws ProductoException {
        if (this.id != 0) {
            try {
                this.producto = this.productoBusiness.getProductoByCode(this.id);
            } catch (ProductoException ex) {
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<SelectItem> getCategorias() throws CategoriaException {
        List<SelectItem> categorias = new ArrayList<SelectItem>();
        CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
        List<Categoria> categoriasCollection = new ArrayList<Categoria>();
        try {
            categoriasCollection = categoriaBusiness.listCategoria();
        } catch (CategoriaException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Categoria c : categoriasCollection) {
            categorias.add(new SelectItem(c.getCatCodigo(), c.getCatNombre()));
        }

        return categorias;
    }

    public List<Producto> getProductos() throws ProductoException {
        List<Producto> productos = new ArrayList<Producto>();
        try {
            productos = this.productoBusiness.listProducto();
        } catch (ProductoException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return productos;
    }
}
