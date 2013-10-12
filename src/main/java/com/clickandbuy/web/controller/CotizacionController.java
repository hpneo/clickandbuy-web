/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.controller;

import clickandbuy.upc.edu.core.business.CotizacionBusiness;
import clickandbuy.upc.edu.core.entity.Cotizacion;
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
@ManagedBean(name = "cotizacionController")
@RequestScoped

public class CotizacionController {
  @ManagedProperty("#{param.id}")
  private int id = 0;
  private Cotizacion cotizacion = new Cotizacion();
  private List<Cotizacion> cotizaciones = new ArrayList<Cotizacion>();
  CotizacionBusiness cotizacionBusiness = new CotizacionBusiness();

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
      Producto producto = this.cotizacionBusiness.getProductoByCode(codProducto);
      this.cotizacion.getProductos().add(producto);

      this.cotizacionBusiness.updateCotizacion(this.cotizacion);
    } catch (Exception ex) {
      Logger.getLogger(CotizacionController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void eliminarProducto(Integer codProducto) {
    try {
      Producto producto = this.cotizacionBusiness.getProductoByCode(codProducto);
      this.cotizacion.getProductos().remove(producto);

      this.cotizacionBusiness.updateCotizacion(this.cotizacion);
    } catch (Exception ex) {
      Logger.getLogger(CotizacionController.class.getName()).log(Level.SEVERE, null, ex);
    }
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
}