
package com.clickandbuy.web.bean;

import java.util.Date;

/**
 *
 * @author GUSTAVO
 */
public class PedidoBean implements java.io.Serializable {
  
  private static final long serialVersionUID = 1L;
  private int codigo;
  private int codigoUsuario;
  private double costoTotal;
  private Date fechaCreacion;
  
  public PedidoBean() {
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public int getCodigoUsuario() {
    return codigoUsuario;
  }

  public void setCodigoUsuario(int codigoUsuario) {
    this.codigoUsuario = codigoUsuario;
  }

  public double getCostoTotal() {
    return costoTotal;
  }

  public void setCostoTotal(double costoTotal) {
    this.costoTotal = costoTotal;
  }

  public Date getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(Date fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }
  
}
