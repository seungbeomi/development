/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.exception;

/**
 * 
 * @since 2013. 3. 16.
 * @author BS정보시스템/손승범
 */
public class ClassLoadException extends ApplicationException {
  
  private static final long serialVersionUID = 1L;
  
  public ClassLoadException(Throwable cause) {
    super(cause);
  }
  
}
