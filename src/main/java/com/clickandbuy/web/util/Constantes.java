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

    private static int SESIONMAX = 60 * 15;
    private static String SESIONUSUARIO = "usuario";
    private static String SESIONCLIENTE = "cliente";
    private static String MENSAJEBIENVENIDA = "Bienvenido";
    private static String MENSAJELOGEOCORRECTO = "Extio";
    private static String MENSAJELOGEOINCORRECTO = "Error al logearse";
    private static String MENUITEMLOGO = "logo";
    private static String MENUITEMURLLOGO = "#{resource['images/cablogo.png']}";
    private static String MENUITEMCATEGORIAS = "Categoria";
    private static String MENUITEMURLCATEGORIAS = "categorias.xhtml";
    private static String MENUITEMUSUARIOS = "Usuarios";
    private static String MENUITEMURLUSUARIOS = "usuarios.xhtml";
    private static String MENUITEMPRODUCTOS = "Productos";
    private static String MENUITEMURLPRODUCTOS = "productos.xhtml";
    private static String MENUITEMPEDIDOS = "Pedidos";
    private static String MENUITEMURLPEDIDOS = "pedidos.xhtml";
    private static String MENUITEMCOTIZACIONES = "Cotizaciones";
    private static String MENUITEMURLCOTIZACIONES = "cotizaciones.xhtml";
    private static String MENUITEMEXIT = "Exit";
    private static String MENUITEMCERRARSESION = "Cerrar sesión";
    private static String TIPODEPEDIDO = "cotizacion";
    private static String PEDIDO = "pedido";

    public static String getPEDIDO() {
        return PEDIDO;
    }
    
    public static int getSESIONMAX() {
        return SESIONMAX;
    }

    public static String getSESIONUSUARIO() {
        return SESIONUSUARIO;
    }

    public static String getSESIONCLIENTE() {
        return SESIONCLIENTE;
    }

    public static String getMENSAJEBIENVENIDA() {
        return MENSAJEBIENVENIDA;
    }

    public static String getMENSAJELOGEOCORRECTO() {
        return MENSAJELOGEOCORRECTO;
    }

    public static String getMENSAJELOGEOINCORRECTO() {
        return MENSAJELOGEOINCORRECTO;
    }

    public static String getMENUITEMLOGO() {
        return MENUITEMLOGO;
    }

    public static String getMENUITEMURLLOGO() {
        return MENUITEMURLLOGO;
    }

    public static String getMENUITEMCATEGORIAS() {
        return MENUITEMCATEGORIAS;
    }

    public static String getMENUITEMURLCATEGORIAS() {
        return MENUITEMURLCATEGORIAS;
    }

    public static String getMENUITEMUSUARIOS() {
        return MENUITEMUSUARIOS;
    }

    public static String getMENUITEMURLUSUARIOS() {
        return MENUITEMURLUSUARIOS;
    }

    public static String getMENUITEMPRODUCTOS() {
        return MENUITEMPRODUCTOS;
    }

    public static String getMENUITEMURLPRODUCTOS() {
        return MENUITEMURLPRODUCTOS;
    }

    public static String getMENUITEMPEDIDOS() {
        return MENUITEMPEDIDOS;
    }

    public static String getMENUITEMURLPEDIDOS() {
        return MENUITEMURLPEDIDOS;
    }

    public static String getMENUITEMCOTIZACIONES() {
        return MENUITEMCOTIZACIONES;
    }

    public static String getMENUITEMURLCOTIZACIONES() {
        return MENUITEMURLCOTIZACIONES;
    }

    public static String getMENUITEMEXIT() {
        return MENUITEMEXIT;
    }

    public static String getMENUITEMCERRARSESION() {
        return MENUITEMCERRARSESION;
    }

    public static String getTIPODEPEDIDO() {
        return TIPODEPEDIDO;
    }
}
