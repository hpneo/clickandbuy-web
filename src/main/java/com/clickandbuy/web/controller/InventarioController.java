/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.controller;

import clickandbuy.upc.edu.core.business.InventarioBusiness;
import clickandbuy.upc.edu.core.entity.Inventario;
import clickandbuy.upc.edu.core.entity.Producto;
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
@ManagedBean(name = "inventarioController")
@RequestScoped

public class InventarioController {
  @ManagedProperty("#{param.id}")
  private int id = 0;
  private Inventario inventario = new Inventario();
  private List<Inventario> inventarios = new ArrayList<Inventario>();
  InventarioBusiness inventarioBusiness = new InventarioBusiness();

  public void insertar() {
    System.out.println("========================");
    System.out.println(this.inventario.getInvCodigo());
    System.out.println("========================");
    try {
      this.inventarioBusiness.addInventario(this.inventario);
    } catch (Exception ex) {
      Logger.getLogger(InventarioController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    WebUtil.redirect("/inventarios/" + this.inventario.getInvCodigo());
  }

  public void actualizar() {
    if (this.inventario != null) {
      System.out.println("========================");
      System.out.println(this.inventario.getInvCodigo());
      System.out.println("========================");

      try {
        this.inventarioBusiness.updateInventario(this.inventario);
      } catch (Exception ex) {
        Logger.getLogger(InventarioController.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      WebUtil.redirect("/inventarios/" + this.id);
    }
  }

  public void agregarProducto(Integer codProducto) {
    try {
      Producto producto = this.inventarioBusiness.getProductoByCode(codProducto);
      this.inventario.getProductos().add(producto);

      this.inventarioBusiness.updateInventario(this.inventario);
    } catch (Exception ex) {
      Logger.getLogger(InventarioController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void eliminarProducto(Integer codProducto) {
    try {
      Producto producto = this.inventarioBusiness.getProductoByCode(codProducto);
      this.inventario.getProductos().remove(producto);

      this.inventarioBusiness.updateInventario(this.inventario);
    } catch (Exception ex) {
      Logger.getLogger(InventarioController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Inventario getInventario() {
    if (this.id == 0) {
      this.inventario = new Inventario();
    }
    else {
      try {
  this.inventario = this.inventarioBusiness.getInventarioByCode(this.id);
      } catch (Exception ex) {
  this.inventario = new Inventario();
      }
    }
    
    return this.inventario;
  }

  public void setInventario(Inventario inventario) {
    this.inventario = inventario;
  }
  
  public List<Inventario> getInventarios() {
    try {
      return inventarioBusiness.listInventario();
    } catch (Exception ex) {
      return new ArrayList<Inventario>();
    }
  }
}