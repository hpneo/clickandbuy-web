/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.controller;

import clickandbuy.upc.edu.core.business.PedidoBusiness;
import clickandbuy.upc.edu.core.entity.Pedido;
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
@ManagedBean(name = "pedidoController")
@RequestScoped

public class PedidoController {
  @ManagedProperty("#{param.id}")
  private int id = 0;
  private Pedido pedido = new Pedido();
  private List<Pedido> pedidos = new ArrayList<Pedido>();
  PedidoBusiness pedidoBusiness = new PedidoBusiness();

  public void insertar() {
    System.out.println("========================");
    System.out.println(this.pedido.getPedCodigo());
    System.out.println("========================");
    try {
      this.pedidoBusiness.addPedido(this.pedido);
    } catch (Exception ex) {
      Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    WebUtil.redirect("/pedidos/" + this.pedido.getPedCodigo());
  }

  public void actualizar() {
    if (this.pedido != null) {
      System.out.println("========================");
      System.out.println(this.pedido.getPedCodigo());
      System.out.println("========================");

      try {
        this.pedidoBusiness.updatePedido(this.pedido);
      } catch (Exception ex) {
        Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      WebUtil.redirect("/pedidos/" + this.id);
    }
  }

  public void agregarProducto(Integer codProducto) {
    try {
      Producto producto = this.pedidoBusiness.getProductoByCode(codProducto);
      this.pedido.getProductos().add(producto);

      this.pedidoBusiness.updatePedido(this.pedido);
    } catch (Exception ex) {
      Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void eliminarProducto(Integer codProducto) {
    try {
      Producto producto = this.pedidoBusiness.getProductoByCode(codProducto);
      this.pedido.getProductos().remove(producto);

      this.pedidoBusiness.updatePedido(this.pedido);
    } catch (Exception ex) {
      Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Pedido getPedido() {
    if (this.id == 0) {
      this.pedido = new Pedido();
    }
    else {
      try {
  this.pedido = this.pedidoBusiness.getPedidoByCode(this.id);
      } catch (Exception ex) {
  this.pedido = new Pedido();
      }
    }
    
    return this.pedido;
  }

  public void setPedido(Pedido pedido) {
    this.pedido = pedido;
  }
  
  public List<Pedido> getPedidos() {
    try {
      return pedidoBusiness.listPedido();
    } catch (Exception ex) {
      return new ArrayList<Pedido>();
    }
  }
}