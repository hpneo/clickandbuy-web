/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.bean;

/**
 *
 * @author GUSTAVO
 */
public class CategoriaBean implements java.io.Serializable {
  
  private static final long serialVersionUID = 1L;
  private int codigo;
  private String nombre;
  
  public CategoriaBean() {
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
  
}
