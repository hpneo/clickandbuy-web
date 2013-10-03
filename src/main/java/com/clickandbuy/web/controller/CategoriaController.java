/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.controller;

import clickandbuy.upc.edu.core.business.CategoriaBusiness;
import clickandbuy.upc.edu.core.entity.Categoria;
import com.clickandbuy.web.bean.CategoriaBean;
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
  private List<Categoria> categorias = new ArrayList<Categoria>();
  CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
  
  public void insertar() {
    System.out.println("========================");
    System.out.println(this.categoria.getCatCodigo());
    System.out.println(this.categoria.getCatCodigo());
    System.out.println("========================");
    
    WebUtil.redirect("/categorias");
  }
  
  public void actualizar() {
    if (this.categoria != null) {
      System.out.println("========================");
      System.out.println(this.categoria.getCatCodigo());
      System.out.println(this.categoria.getCatNombre());
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

  public Categoria getCategoria() {
    if (this.id == 0) {
      this.categoria = new Categoria();
    }
    else {
      try {
	this.categoria = this.categoriaBusiness.getCategoriaByCode(this.id);
      } catch (Exception ex) {
	this.categoria = new Categoria();
      }
    }
    
    return this.categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }
  
  public List<Categoria> getCategorias() {
    try {
      return categoriaBusiness.listCategoria();
    } catch (Exception ex) {
      return new ArrayList<Categoria>();
    }
  }
}
