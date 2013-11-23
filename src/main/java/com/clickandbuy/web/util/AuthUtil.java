package com.clickandbuy.web.util;

/**
 *
 * @author GUSTAVO
 */
public class AuthUtil {

    public static boolean userSignedIn() {
        return WebUtil.getObjectSesion(Constantes.getSESION_USUARIO()) != null;
    }

    public static boolean clienteSignedIn() {
        return WebUtil.getObjectSesion(Constantes.getSESION_CLIENTE()) != null;
    }
}
