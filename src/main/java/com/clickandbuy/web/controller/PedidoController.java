/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.controller;

import clickandbuy.upc.edu.core.business.PedidoBusiness;
import clickandbuy.upc.edu.core.business.ProductoBusiness;
import clickandbuy.upc.edu.core.entity.Pedido;
import clickandbuy.upc.edu.core.entity.PedidoDetalle;
import clickandbuy.upc.edu.core.entity.Producto;
import com.clickandbuy.web.util.WebUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.*;
import javax.faces.model.SelectItem;

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
  private PedidoDetalle pedidoDetalle = new PedidoDetalle();
  private List<Pedido> pedidos = new ArrayList<Pedido>();
  PedidoBusiness pedidoBusiness = new PedidoBusiness();
  ProductoBusiness productoBusiness = new ProductoBusiness();
  private List<SelectItem> productos = new ArrayList<SelectItem>();

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

  public void agregarProducto() {
    try {
      this.pedido.getItems().add(this.pedidoDetalle);

      this.pedidoBusiness.updatePedido(this.pedido);
    } catch (Exception ex) {
      Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    WebUtil.redirect("/pedidos/" + this.id);
  }

  public void eliminarProducto(Integer codPedidoDetalle) {
    try {
      PedidoDetalle pedidoDetalle = this.pedidoBusiness.getPedidoDetalleByCode(codPedidoDetalle);
      this.pedido.getItems().remove(pedidoDetalle);

      this.pedidoBusiness.updatePedido(this.pedido);
    } catch (Exception ex) {
      Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    WebUtil.redirect("/pedidos/" + this.id);
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
  
  public PedidoDetalle getPedidoDetalle() {
    return this.pedidoDetalle;
  }
  
  public void setPedidoDetalle(PedidoDetalle pedidoDetalle) {
    this.pedidoDetalle = pedidoDetalle;
  }
  
  public List<SelectItem> getProductos() {
    this.productos = new ArrayList<SelectItem>();
    
    List<Producto> _productos;
    
    try {
      _productos = this.productoBusiness.listProducto();
    } catch (Exception ex) {
      _productos = new ArrayList<Producto>();
    }
    
    for (Producto c:_productos) {
      productos.add(new SelectItem(c.getProdCodigo(), c.getProdNombre()));
    }
    
    return productos;
  }
  
}