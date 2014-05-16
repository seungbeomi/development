/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <pre>
 * 시  스  템 : 
 * 프로그램ID : WebUtil.java
 * 프로그램명 : 
 * 설      명 : 
 * 작  성  자 : BS정보시스템/손승범
 * 작  성  일 : 2013. 11. 15.
 * </pre>
 */
public class WebUtil {
  
  /**
   * 호스트명 반환
   * 
   * @return
   * @throws UnknownHostException
   */
  public static String getHostName() throws UnknownHostException {
    return InetAddress.getLocalHost().getHostName();
  }
  
  /**
   * 호스트 주소 반환
   * 
   * @return
   * @throws UnknownHostException
   */
  public static String getHostAddress() throws UnknownHostException {
    return InetAddress.getLocalHost().getHostAddress();
  }
  
}
