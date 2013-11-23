/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.util;

/**
 *
 * @author Piero
 */
public final class Constantes {

    private static int SESION_MAX = 60 * 15; 
    private static String SESION_USUARIO = "usuario";
    private static String SESION_CLIENTE = "cliente";

    public static int getSESION_MAX() {
        return SESION_MAX;
    }

    public static String getSESION_USUARIO() {
        return SESION_USUARIO;
    }

    public static String getSESION_CLIENTE() {
        return SESION_CLIENTE;
    }

    public static String getMENSAJE_BIENVENIDA() {
        return MENSAJE_BIENVENIDA;
    }

    public static String getMENSAJE_LOGEO_CORRECTO() {
        return MENSAJE_LOGEO_CORRECTO;
    }

    public static String getMENSAJE_LOGEO_INCORRECTO() {
        return MENSAJE_LOGEO_INCORRECTO;
    }

    public static String getMENU_ITEM_LOGO() {
        return MENU_ITEM_LOGO;
    }

    public static String getMENU_ITEM_URLLOGO() {
        return MENU_ITEM_URLLOGO;
    }

    public static String getMENU_ITEM_CATEGORIAS() {
        return MENU_ITEM_CATEGORIAS;
    }

    public static String getMENU_ITEM_URLCATEGORIAS() {
        return MENU_ITEM_URLCATEGORIAS;
    }

    public static String getMENU_ITEM_USUARIOS() {
        return MENU_ITEM_USUARIOS;
    }

    public static String getMENU_ITEM_URLUSUARIOS() {
        return MENU_ITEM_URLUSUARIOS;
    }

    public static String getMENU_ITEM_PRODUCTOS() {
        return MENU_ITEM_PRODUCTOS;
    }

    public static String getMENU_ITEM_URLPRODUCTOS() {
        return MENU_ITEM_URLPRODUCTOS;
    }

    public static String getMENU_ITEM_PEDIDOS() {
        return MENU_ITEM_PEDIDOS;
    }

    public static String getMENU_ITEM_URLPEDIDOS() {
        return MENU_ITEM_URLPEDIDOS;
    }

    public static String getMENU_ITEM_COTIZACIONES() {
        return MENU_ITEM_COTIZACIONES;
    }

    public static String getMENU_ITEM_URLCOTIZACIONES() {
        return MENU_ITEM_URLCOTIZACIONES;
    }

    public static String getMENU_ITEM_EXIT() {
        return MENU_ITEM_EXIT;
    }

    public static String getMENU_ITEM_CERRAR_SESION() {
        return MENU_ITEM_CERRAR_SESION;
    }

    public static String getTIPO_DE_PEDIDO() {
        return TIPO_DE_PEDIDO;
    }
    private static String MENSAJE_BIENVENIDA = "Bienvenido";
    private static String MENSAJE_LOGEO_CORRECTO = "Extio";
    private static String MENSAJE_LOGEO_INCORRECTO = "Error al logearse";
    private static String MENU_ITEM_LOGO = "logo";
    private static String MENU_ITEM_URLLOGO = "#{resource['images/cab_logo.png']}";
    private static String MENU_ITEM_CATEGORIAS = "Categoria";
    private static String MENU_ITEM_URLCATEGORIAS = "categorias.xhtml";
    private static String MENU_ITEM_USUARIOS = "Usuarios";
    private static String MENU_ITEM_URLUSUARIOS = "usuarios.xhtml";
    private static String MENU_ITEM_PRODUCTOS = "Productos";
    private static String MENU_ITEM_URLPRODUCTOS = "productos.xhtml";
    private static String MENU_ITEM_PEDIDOS = "Pedidos";
    private static String MENU_ITEM_URLPEDIDOS = "pedidos.xhtml";
    private static String MENU_ITEM_COTIZACIONES = "Cotizaciones";
    private static String MENU_ITEM_URLCOTIZACIONES = "cotizaciones.xhtml";
    public static String MENU_ITEM_EXIT = "Exit";
    public static String MENU_ITEM_CERRAR_SESION = "Cerrar sesión";
    private static String TIPO_DE_PEDIDO = "cotizacion";
}
