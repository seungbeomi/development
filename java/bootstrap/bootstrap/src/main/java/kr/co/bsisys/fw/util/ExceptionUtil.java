/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.util;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * 예외처리 출력을 위한 유틸클래스
 * 
 * @since 2013. 5. 17.
 * @author BS정보시스템/손승범
 */
public final class ExceptionUtil {
  
  public static String getStackTrace(Throwable throwable) {
    return ExceptionUtils.getStackTrace(throwable);
  }
  
}
