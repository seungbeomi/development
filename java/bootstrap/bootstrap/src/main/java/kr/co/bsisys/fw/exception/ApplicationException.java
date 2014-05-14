/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.exception;

/**
 * 어플리케이션 예외 클래스
 * 
 * @since 2014. 5. 9.
 * @author IT지원/과장/손승범
 */
public class ApplicationException extends RuntimeException {
  
  private String errorCode = null;
  
  private Object[] args = null;
  
  private String message = null;
  
  public ApplicationException() {
    super();
  }
  
  public ApplicationException(String errorCode) {
    super();
    this.errorCode = errorCode;
  }
  
  public ApplicationException(String errorCode, Object... args) {
    super();
    this.errorCode = errorCode;
    this.args = args;
  }
  
  public ApplicationException(Throwable cause) {
    super(cause);
    this.errorCode = "";
  }
  
  public ApplicationException(Throwable cause, String errorCode) {
    super(cause);
    this.errorCode = errorCode;
  }
  
  public ApplicationException(Throwable cause, String errorCode, Object... args) {
    super(cause);
    this.errorCode = errorCode;
    this.args = args;
  }
  
  public String getErrorCode() {
    return this.errorCode;
  }
  
  public Object[] getArgs() {
    return this.args;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  @Override
  public String getMessage() {
    if (this.message == null) {
      return this.errorCode;
    }
    return this.message;
  }
  
}
