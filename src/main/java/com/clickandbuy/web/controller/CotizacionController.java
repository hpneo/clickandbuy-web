/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.controller;

import clickandbuy.upc.edu.core.business.CotizacionBusiness;
import clickandbuy.upc.edu.core.business.ProductoBusiness;
import clickandbuy.upc.edu.core.entity.Cotizacion;
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
  private Cotizacion cotizacion = new Cotizacion();
  private CotizacionDetalle cotizacionDetalle = new CotizacionDetalle();
  private List<Cotizacion> cotizaciones = new ArrayList<Cotizacion>();
  CotizacionBusiness cotizacionBusiness = new CotizacionBusiness();
  ProductoBusiness productoBusiness = new ProductoBusiness();
  private List<SelectItem> productos = new ArrayList<SelectItem>();

  public void insertar() {
    System.out.println("========================");
    System.out.println(this.cotizacion.getCotCodigo());
    System.out.println("========================");
    try {
      this.cotizacionBusiness.addCotizacion(this.cotizacion);
    } catch (Exception ex) {
      Logger.getLogger(CotizacionController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    WebUtil.redirect("/cotizaciones/" + this.cotizacion.getCotCodigo());
  }

  public void actualizar() {
    if (this.cotizacion != null) {
      System.out.println("========================");
      System.out.println(this.cotizacion.getCotCodigo());
      System.out.println("========================");

      try {
        this.cotizacionBusiness.updateCotizacion(this.cotizacion);
      } catch (Exception ex) {
        Logger.getLogger(CotizacionController.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      WebUtil.redirect("/cotizaciones/" + this.id);
    }
  }

  public void agregarProducto(Integer codProducto) {
    try {
      this.cotizacion.getItems().add(this.cotizacionDetalle);

      this.cotizacionBusiness.updateCotizacion(this.cotizacion);
    } catch (Exception ex) {
      Logger.getLogger(CotizacionController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    WebUtil.redirect("/cotizaciones/" + this.id);
  }

  public void eliminarProducto(Integer codCotizacionDetalle) {
    try {
      CotizacionDetalle cotizacionDetalle = this.cotizacionBusiness.getCotizacionDetalleByCode(codCotizacionDetalle);
      this.cotizacion.getItems().remove(cotizacionDetalle);

      this.cotizacionBusiness.updateCotizacion(this.cotizacion);
    } catch (Exception ex) {
      Logger.getLogger(CotizacionController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    WebUtil.redirect("/cotizaciones/" + this.id);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Cotizacion getCotizacion() {
    if (this.id == 0) {
      this.cotizacion = new Cotizacion();
    }
    else {
      try {
  this.cotizacion = this.cotizacionBusiness.getCotizacionByCode(this.id);
      } catch (Exception ex) {
  this.cotizacion = new Cotizacion();
      }
    }
    
    return this.cotizacion;
  }

  public void setCotizacion(Cotizacion cotizacion) {
    this.cotizacion = cotizacion;
  }
  
  public List<Cotizacion> getCotizaciones() {
    try {
      return cotizacionBusiness.listCotizacion();
    } catch (Exception ex) {
      return new ArrayList<Cotizacion>();
    }
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