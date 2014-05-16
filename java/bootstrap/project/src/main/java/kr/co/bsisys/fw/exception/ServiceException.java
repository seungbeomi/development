/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.exception;

/**
 * 컨트롤러 예외 클래스
 * 
 * @since 2014. 5. 9.
 * @author IT지원/과장/손승범
 */
public class ServiceException extends ApplicationException {
  
  public ServiceException() {
    super();
  }
  
  public ServiceException(Throwable cause) {
    super(cause);
  }
  
  public ServiceException(Throwable cause, String errorCode) {
    super(cause, errorCode);
  }
  
  public ServiceException(Throwable cause, String errorCode, Object... args) {
    super(cause, errorCode, args);
  }
  
  public ServiceException(String errorCode, Object... args) {
    super(errorCode, args);
  }
  
}
