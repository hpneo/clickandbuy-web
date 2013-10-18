/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.controller;

import clickandbuy.upc.edu.core.business.PedidoBusiness;
import clickandbuy.upc.edu.core.business.ProductoBusiness;
import clickandbuy.upc.edu.core.business.ProductoxpedidoBusinees;
import clickandbuy.upc.edu.core.entity.Pedido;
import clickandbuy.upc.edu.core.entity.Productoxpedido;
import clickandbuy.upc.edu.core.entity.ProductoxpedidoId;
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
@ManagedBean(name = "cotizacionController")
@RequestScoped

public class CotizacionController {
  @ManagedProperty("#{param.id}")
  private int id = 0;
  private Pedido cotizacion = new Pedido();
  private Productoxpedido cotizacionDetalle = new Productoxpedido();
  private List<Pedido> cotizaciones = new ArrayList<Pedido>();
  PedidoBusiness pedidoBusiness = new PedidoBusiness();
  ProductoBusiness productoBusiness = new ProductoBusiness();
  ProductoxpedidoBusinees productoxpedidoBusinees = new ProductoxpedidoBusinees();
  private List<SelectItem> productos = new ArrayList<SelectItem>();

  public void insertar() {
    System.out.println("========================");
    System.out.println(this.cotizacion.getPedCodigo());
    System.out.println("========================");
    try {
      this.pedidoBusiness.addPedido(this.cotizacion);
    } catch (Exception ex) {
      Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    WebUtil.redirect("/pedidos/" + this.cotizacion.getPedCodigo());
  }

  public void actualizar() {
    if (this.cotizacion != null) {
      System.out.println("========================");
      System.out.println(this.cotizacion.getPedCodigo());
      System.out.println("========================");

      try {
        this.pedidoBusiness.updatePedido(this.cotizacion);
      } catch (Exception ex) {
        Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      WebUtil.redirect("/pedidos/" + this.id);
    }
  }

  public void agregarProducto() {
    try {
      this.cotizacionDetalle.setPedido(this.cotizacion);
      this.productoxpedidoBusinees.addProductoxpedido(this.cotizacionDetalle);
    } catch (Exception ex) {
      Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    WebUtil.redirect("/pedidos/" + this.id);
  }

  public void eliminarProducto(Integer codProducto) {
    try {
      ProductoxpedidoId codPedido = new ProductoxpedidoId(this.id, codProducto);
      Productoxpedido pedidoDetalle = this.productoxpedidoBusinees.getProductoxpedido(codPedido);

      this.productoxpedidoBusinees.deleteProductoxpedido(pedidoDetalle);
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

  public Pedido getCotizacion() {
    if (this.id == 0) {
      this.cotizacion = new Pedido();
    }
    else {
      try {
	this.cotizacion = this.pedidoBusiness.getPedido(this.id);
      } catch (Exception ex) {
	this.cotizacion = new Pedido();
      }
    }
    
    return this.cotizacion;
  }

  public void setCotizacion(Pedido cotizacion) {
    this.cotizacion = cotizacion;
  }
  
  public List<Pedido> getCotizaciones() {
    try {
      return pedidoBusiness.listPedidoxTipo("cotizacion");
    } catch (Exception ex) {
      return new ArrayList<Pedido>();
    }
  }
  
  public Productoxpedido getCotizacionDetalle() {
    return this.cotizacionDetalle;
  }
  
  public void setCotizacionDetalle(Productoxpedido pedidoDetalle) {
    this.cotizacionDetalle = pedidoDetalle;
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