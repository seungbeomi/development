/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.util;

/**
 * <pre>
 * 시  스  템 : 공통
 * 프로그램ID : BooleanUtil.java
 * 프로그램명 : Boolean 유틸
 * 설      명 : Boolean 유틸 클래스
 * 작  성  자 : BS정보시스템/손승범
 * 작  성  일 : 2013. 11. 19.
 * </pre>
 */
public class BooleanUtil {
  
  /**
   * 문자열의 값이 참일지 확인
   * 
   * @param str
   * @return
   */
  public static boolean isTrue(String str) {
    boolean result = false;
    // 값이 존재하는 경우
    if (StringUtil.isNotEmpty(str)) {
      if ("Y".equalsIgnoreCase(str) || "TRUE".equalsIgnoreCase(str)) {
        result = true;
      }
    }
    return result;
  }
  
  /**
   * 문자열의 값이 거짓인지 확인
   * 
   * @param str
   * @return
   */
  public static boolean isFalse(String str) {
    return !isTrue(str);
  }
  
  /**
   * boolean값을 0(false), 1(true) 으로 반환
   * 
   * @param value
   * @return
   */
  public static int parseInt(boolean value) {
    int result = 0;
    
    if (value) {
      result = 1;
    }
    
    return result;
    
  }
  
}
