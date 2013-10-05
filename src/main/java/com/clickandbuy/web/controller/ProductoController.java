/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.controller;

import clickandbuy.upc.edu.core.business.CategoriaBusiness;
import clickandbuy.upc.edu.core.business.ProductoBusiness;
import clickandbuy.upc.edu.core.entity.Categoria;
import clickandbuy.upc.edu.core.entity.Producto;
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
  private List<Producto> productos = new ArrayList<Producto>();
  private List<SelectItem> categorias = new ArrayList<SelectItem>();
  private ProductoBusiness productoBusiness = new ProductoBusiness();
  
  public void insertar() {
    System.out.println("========================");
    System.out.println(this.producto.getProdCodigo());
    System.out.println(this.producto.getProdNombre());
    System.out.println("========================");
    
    try {
      this.productoBusiness.addProducto(this.producto);
    } catch (Exception ex) {
      Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    WebUtil.redirect("/productos");
  }
  
  public void actualizar() {
    if (this.producto != null) {
      System.out.println("========================");
      System.out.println(this.producto.getProdCodigo());
      System.out.println(this.producto.getProdNombre());
      System.out.println("========================");
      
      WebUtil.redirect("/productos/" + this.id);
    }
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
  
  public Producto getProducto() {
    if (this.id == 0) {
      this.producto = new Producto();
    }
    else {
      try {
	this.producto = this.productoBusiness.getProductoByCode(this.id);
      } catch (Exception ex) {
	this.producto = new Producto();
      }
    }
    
    return this.producto;
  }
  
  public void setProducto(Producto producto) {
    this.producto = producto;
  }
  
  public List<SelectItem> getCategorias() {
    this.categorias = new ArrayList<SelectItem>();
    
    CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
    List<Categoria> _categorias;
    
    try {
      _categorias = categoriaBusiness.listCategoria();
    } catch (Exception ex) {
      _categorias = new ArrayList<Categoria>();
    }
    
    for (Categoria c:_categorias) {
      categorias.add(new SelectItem(c.getCatCodigo(), c.getCatNombre()));
    }
    
    return categorias;
  }
  
  public List<Producto> getProductos() {
    try {
      return this.productoBusiness.listProducto();
    } catch (Exception e) {
      return new ArrayList<Producto>();
    }
  }
  
}
