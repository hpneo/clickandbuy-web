/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.util;

/**
 *
 * @author Piero
 */
public final class Constantes 
{
    public static int SESION_MAX = 60 * 15; //La sesion que dure 15 minutos
    public static String SESION_USUARIO="usuario";
    public static String SESION_CLIENTE="cliente";
    
    public static String MENSAJE_BIENVENIDA="Bienvenido";
    public static String MENSAJE_LOGEO_CORRECTO="Extio";
    public static String MENSAJE_LOGEO_INCORRECTO="Error al logearse";
    
    public static String MENU_ITEM_LOGO="logo";
    public static String MENU_ITEM_URLLOGO="#{resource['images/cab_logo.png']}";
    public static String MENU_ITEM_CATEGORIAS="Categoria";
    public static String MENU_ITEM_URLCATEGORIAS="categorias.xhtml";
    public static String MENU_ITEM_USUARIOS="Usuarios";
    public static String MENU_ITEM_URLUSUARIOS="usuarios.xhtml";
    public static String MENU_ITEM_PRODUCTOS="Productos";
    public static String MENU_ITEM_URLPRODUCTOS="productos.xhtml";
    public static String MENU_ITEM_PEDIDOS="Pedidos";
    public static String MENU_ITEM_URLPEDIDOS="pedidos.xhtml";
    public static String MENU_ITEM_COTIZACIONES="Cotizaciones";
    public static String MENU_ITEM_URLCOTIZACIONES="cotizaciones.xhtml";
    
     public static String MENU_ITEM_EXIT="Exit";
      public static String MENU_ITEM_CERRAR_SESION="Cerrar sesión";
      
      private static String TIPO_DE_PEDIDO="cotizacion";
}
