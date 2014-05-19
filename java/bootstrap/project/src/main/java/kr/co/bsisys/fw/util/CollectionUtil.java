/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.util;

import java.util.List;

/**
 * <pre>
 * 시  스  템 : 
 * 프로그램ID : CollectionUtil.java
 * 프로그램명 : 
 * 설      명 : 
 * 작  성  자 : BS정보시스템/손승범
 * 작  성  일 : 2013. 11. 25.
 * </pre>
 */
public class CollectionUtil {
  
  public static boolean isEmpty(List<?> list) {
    return list == null || list.size() == 0;
  }
  
  public static boolean isNotEmpty(List<?> list) {
    return !isEmpty(list);
  }
  
}
