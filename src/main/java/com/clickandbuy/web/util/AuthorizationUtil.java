/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickandbuy.web.util;

/**
 *
 * @author Piero
 */
public class AuthorizationUtil 
{
    public static boolean userSignedIn() 
    {
        return WebUtil.getSession(Constantes.SESION_USUARIO) == null;
  }
}
