/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.bean;

/**
 *
 * @author GUSTAVO
 */
public class ClienteBean implements java.io.Serializable {
  
  private static final long serialVersionUID = 1L;
  private String nombreUsuario;
  private String clave;
  private String nombres;
  private String apellidos;
  private int dni;
  private String nombreEmpresa;
  private String direccion;
  private int ruc;
  private int celular;
  private String direccionEmpresa;
  private int telefonoEmpresa;
  
  public ClienteBean() {
  }

  public String getNombreUsuario() {
    return nombreUsuario;
  }

  public void setNombreUsuario(String nombreUsuario) {
    this.nombreUsuario = nombreUsuario;
  }

  public String getClave() {
    return clave;
  }

  public void setClave(String clave) {
    this.clave = clave;
  }

  public String getNombres() {
    return nombres;
  }

  public void setNombres(String nombres) {
    this.nombres = nombres;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  public int getDni() {
    return dni;
  }

  public void setDni(int dni) {
    this.dni = dni;
  }

  public String getNombreEmpresa() {
    return nombreEmpresa;
  }

  public void setNombreEmpresa(String nombreEmpresa) {
    this.nombreEmpresa = nombreEmpresa;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public int getRuc() {
    return ruc;
  }

  public void setRuc(int ruc) {
    this.ruc = ruc;
  }

  public int getCelular() {
    return celular;
  }

  public void setCelular(int celular) {
    this.celular = celular;
  }

  public String getDireccionEmpresa() {
    return direccionEmpresa;
  }

  public void setDireccionEmpresa(String direccionEmpresa) {
    this.direccionEmpresa = direccionEmpresa;
  }

  public int getTelefonoEmpresa() {
    return telefonoEmpresa;
  }

  public void setTelefonoEmpresa(int telefonoEmpresa) {
    this.telefonoEmpresa = telefonoEmpresa;
  }
  
}
