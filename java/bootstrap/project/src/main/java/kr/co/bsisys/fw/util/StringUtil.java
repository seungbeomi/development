/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.util;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import kr.co.bsisys.fw.Constants;

import org.apache.commons.lang.StringUtils;

/**
 * <pre>
 * 시  스  템 : 공통 
 * 프로그램ID : StringUtil.java
 * 프로그램명 : String 유틸
 * 설      명 : 문자열 유틸 클래스
 * 작  성  자 : BS정보시스템/손승범
 * 작  성  일 : 2013. 11. 14.
 * </pre>
 */
public class StringUtil {
  
  public static final String EMPTY = "";
  public static final String SPACE = " ";
  
  /**
   * 문자열이 비었는지 확인
   * 
   * @param str
   * @return
   */
  public static boolean isEmpty(String str) {
    return str == null || str.length() == 0;
  }
  
  /**
   * 문자열이 비어 있지 않은지 확인
   * 
   * @param str
   * @return
   */
  public static boolean isNotEmpty(String str) {
    return !isEmpty(str);
  }
  
  public static boolean isY(String str) {
    boolean result = false;
    
    if (isNotEmpty(str) && "Y".equals(str)) {
      result = true;
    }
    
    return result;
  }
  
  public static boolean isN(String str) {
    return !isY(str);
  }
  
  /**
   * 빈문자열 설정
   * 
   * @param str
   * @return
   */
  public static String defaultValue(String str, String defaultValue) {
    String result = EMPTY;
    
    if (isNotEmpty(str)) {
      result = str;
    } else if (isNotEmpty(defaultValue)) {
      result = defaultValue;
    }
    
    return result;
  }
  
  /**
   * 빈문자열 설정
   * 
   * @param str
   * @return
   */
  public static String defaultValue(String str) {
    return defaultValue(str, null);
  }
  
  /**
   * "↕"로 구분되어 있고 locale에 맞는 문자열 반환
   * 
   * @param str
   * @param locale
   * @return
   */
  public static String getText(String str, Locale locale) {
    String result = "";
    
    if (isNotEmpty(str)) {
      int idx = str.indexOf(Constants.SEPARATOR);
      if (idx < 0) {
        result = str;
      } else {
        String lang = locale.getLanguage();
        if ("ko".equals(lang)) {
          result = str.substring(0, idx).trim();
        } else if ("en".equals(lang)) {
          result = str.substring(0, idx + 1).trim();
        } else {
          result = str;
        }
      }
    }
    
    return result;
  }
  
  /** null값일 경우 빈값반환 */
  public static String nvl(String str) {
    return isNotEmpty(str) ? str : EMPTY;
  }
  
  /** 문자열을 end 사이즈까지 SubString 시킴 */
  public static String subStringEnd(String str, int end) {
    return StringUtils.substring(str, 0, end);
  }
  
  /** 문자열을 start 사이즈부터 SubString 시킴 */
  public static String subString(String str, int start) {
    return StringUtils.substring(str, start);
  }
  
  /** 문자열을 start 사이즈부터 end사이즈까지 SubString 시킴 */
  public static String subString(String str, int start, int end) {
    return StringUtils.substring(str, start, end);
  }
  
  /** 문자열을 바이트 end 사이즈까지 SubString 시킴 : UTF-8 */
  public static String subStringEndB(String str, int end) {
    return subStringEndB(str, end, "UTF-8");
  }
  
  /** 문자열을 바이트 end 사이즈까지 SubString 시킴 */
  public static String subStringEndB(String str, int end, String charset) {
    StringBuffer sb = new StringBuffer();
    int cnt = 0;
    try {
      for (int i = 0; i < str.length(); i++) {
        String tmpStr = str.substring(i, i + 1);
        byte[] b = tmpStr.getBytes(charset);
        if (cnt + b.length > end) {
          return sb.toString();
        } else {
          sb.append(tmpStr);
          cnt += b.length;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return sb.toString();
  }
  
  /**
   * 빈문자열인지 확인.
   * 
   * @param str
   * @return
   */
  public static boolean isWhitespace(String str) {
    return StringUtils.isWhitespace(str);
  }
  
  /**
   * 앞뒤 빈공간을 삭제.
   * 
   * @param str
   * @return
   */
  public static String trim(String str) {
    return StringUtils.trim(str);
  }
  
  /**
   * 확장자를 반환.
   * 
   * @param name
   * @return
   */
  public static String getExtension(String name) {
    if (name == null) {
      return null;
    }
    
    int index = name.lastIndexOf('.');
    
    return (index < 0) ? "" : name.substring(index);
  }
  
  /**
   * 헥사코드로 변환.
   * 
   * @param byteArray
   * @param delim
   * @return
   */
  public static String toHexString(byte[] byteArray, String delim) {
    if (delim == null) {
      delim = "";
    }
    
    StringBuilder sb = new StringBuilder();
    
    for (int i = 0; i < byteArray.length; i++) {
      if (i > 0) {
        sb.append(delim);
      }
      String hex = Integer.toHexString(byteArray[i] & 0x00ff).toUpperCase();
      if (hex.length() < 2) {
        sb.append("0");
      }
      sb.append(hex);
    }
    
    return sb.toString();
  }
  
  /**
   * 첫문자를 대문자로 변환.
   * 
   * @param str
   * @return
   */
  public static String capitalize(String str) {
    return StringUtils.capitalize(str);
  }
  
  public static boolean hasText(String str) {
    return org.springframework.util.StringUtils.hasText(str);
  }
  
  public static boolean hasLength(String str) {
    return org.springframework.util.StringUtils.hasLength(str);
  }
  
  /**
   * 문자열을 바이트코드의 길이를 반환.
   * 
   * @param value
   * @param encoding
   * @return
   * @throws UnsupportedEncodingException
   */
  public static int getByteLength(String value, String encoding) throws UnsupportedEncodingException {
    if (value == null || "".equals(value)) {
      return 0;
    }
    
    byte[] bytes = null;
    
    if (encoding == null || "".equals(encoding)) {
      bytes = value.getBytes();
    } else {
      try {
        bytes = value.getBytes(encoding);
      } catch (UnsupportedEncodingException e) {
        throw e;
      }
    }
    
    return bytes == null ? 0 : bytes.length;
  }
  
  /**
   * 카멜케이스로 변환.
   * 
   * @param underScore
   * @return
   */
  public static String convert2CamelCase(String underScore) {
    if (underScore.indexOf('_') < 0 && Character.isLowerCase(underScore.charAt(0))) {
      return underScore;
    }
    StringBuilder result = new StringBuilder();
    boolean nextUpper = false;
    int len = underScore.length();
    
    for (int i = 0; i < len; i++) {
      char currentChar = underScore.charAt(i);
      if (currentChar == '_') {
        nextUpper = true;
      } else {
        if (nextUpper) {
          result.append(Character.toUpperCase(currentChar));
          nextUpper = false;
        } else {
          result.append(Character.toLowerCase(currentChar));
        }
      }
    }
    return result.toString();
  }
}
