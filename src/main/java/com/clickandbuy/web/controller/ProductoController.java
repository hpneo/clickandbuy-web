/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.controller;

import com.clickandbuy.web.bean.ProductoBean;
import com.clickandbuy.web.util.WebUtil;
import java.util.ArrayList;
import java.util.List;
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
  private ProductoBean producto = new ProductoBean();
  private List<SelectItem> categorias = new ArrayList<SelectItem>();
  
  public void insertar() {
    System.out.println("========================");
    System.out.println(this.producto.getCodigo());
    System.out.println(this.producto.getNombre());
    System.out.println("========================");
    
    WebUtil.redirect("/productos");
  }
  
  public void actualizar() {
    if (this.producto != null) {
      System.out.println("========================");
      System.out.println(this.producto.getCodigo());
      System.out.println(this.producto.getNombre());
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
  
  public ProductoBean getProducto() {
    if (this.id == 0) {
      this.producto = new ProductoBean();
    }
    else {
      this.producto = new ProductoBean(); //ProductoBusiness.obtener(this.id);
      this.producto.setCodigo(this.id);
      this.producto.setNombre("Galletas Oreo (x6 unidades)");
      this.producto.setCodigoCategoria(1);
    }
    
    return this.producto;
  }
  
  public void setProductoBean(ProductoBean producto) {
    this.producto = producto;
  }
  
  public List<SelectItem> getCategorias() {
    this.categorias = new ArrayList<SelectItem>();
    categorias.add(new SelectItem(1, "Abarrotes"));
    categorias.add(new SelectItem(2, "Cuidado personal"));
    categorias.add(new SelectItem(3, "Limpieza"));
    
    return categorias;
  }
  
}
