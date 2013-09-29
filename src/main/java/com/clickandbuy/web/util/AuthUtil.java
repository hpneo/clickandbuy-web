package com.clickandbuy.web.util;

/**
 *
 * @author GUSTAVO
 */
public class AuthUtil {
  
  public static boolean userSignedIn() {
    return WebUtil.getSession("current_user") == null;
  }
  
}
