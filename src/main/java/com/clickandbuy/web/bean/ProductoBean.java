/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.bean;

/**
 *
 * @author GUSTAVO
 */
public class ProductoBean implements java.io.Serializable {
  
  private static final long serialVersionUID = 1L;
  private int codigo;
  private String nombre;
  private int stock;
  private int codigoCategoria;
  private String descripcion;
  private double precioCompra;
  private double precioVenta;
  
  public ProductoBean() {
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public int getCodigoCategoria() {
    return codigoCategoria;
  }

  public void setCodigoCategoria(int codigoCategoria) {
    this.codigoCategoria = codigoCategoria;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public double getPrecioCompra() {
    return precioCompra;
  }

  public void setPrecioCompra(double precioCompra) {
    this.precioCompra = precioCompra;
  }

  public double getPrecioVenta() {
    return precioVenta;
  }

  public void setPrecioVenta(double precioVenta) {
    this.precioVenta = precioVenta;
  }
  
}
