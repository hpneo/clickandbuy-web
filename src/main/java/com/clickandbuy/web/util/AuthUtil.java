package com.clickandbuy.web.util;

/**
 *
 * @author GUSTAVO
 */
public class AuthUtil {

    public static boolean userSignedIn() {
        return WebUtil.getObjectSesion(Constantes.getSESIONUSUARIO()) != null;
    }

    public static boolean clienteSignedIn() {
        return WebUtil.getObjectSesion(Constantes.getSESIONCLIENTE()) != null;
    }
}
