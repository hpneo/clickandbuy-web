/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.controller;

import com.clickandbuy.web.bean.CategoriaBean;
import com.clickandbuy.web.util.WebUtil;
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
  private CategoriaBean categoria = new CategoriaBean();
  
  public void insertar() {
    System.out.println("========================");
    System.out.println(this.categoria.getCodigo());
    System.out.println(this.categoria.getNombre());
    System.out.println("========================");
    
    WebUtil.redirect("/categorias");
  }
  
  public void actualizar() {
    if (this.categoria != null) {
      System.out.println("========================");
      System.out.println(this.categoria.getCodigo());
      System.out.println(this.categoria.getNombre());
      System.out.println("========================");
      
      WebUtil.redirect("/categorias/" + this.id);
    }
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public CategoriaBean getCategoria() {
    if (this.id == 0) {
      this.categoria = new CategoriaBean();
    }
    else {
      this.categoria = new CategoriaBean(); //CategoriaBusiness.obtener(this.id);
      this.categoria.setCodigo(this.id);
      this.categoria.setNombre("Abarrotes");
    }
    
    return this.categoria;
  }

  public void setCategoria(CategoriaBean categoria) {
    this.categoria = categoria;
  }
}
