package com.clickandbuy.web.bean;

import java.util.Date;

/**
 *
 * @author GUSTAVO
 */
public class UsuarioBean implements java.io.Serializable {
  
  private static final long serialVersionUID = 1L;
  private String nombreUsuario;
  private String clave;
  private String nombres;
  private String apellidos;
  private int dni;
  private int celular;
  private String correo;
  private int codigoRol;
  private Date fechaNacimiento;
  private String direccion;
  
  public UsuarioBean() {
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

  public int getCelular() {
    return celular;
  }

  public void setCelular(int celular) {
    this.celular = celular;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public int getCodigoRol() {
    return codigoRol;
  }

  public void setCodigoRol(int codigoRol) {
    this.codigoRol = codigoRol;
  }

  public Date getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }
  
}
