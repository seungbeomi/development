/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.com.biz.message;

import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

/**
 * 메세지 코드와 변수를 이용하에 메세지를 구하는 클래스.
 * 
 * @since 2013. 3. 16.
 * @author BS정보시스템/손승범
 */
@Component
public class MessageAccessorImpl extends ApplicationObjectSupport implements MessageAccessor {
  
  /*
   * (non-Javadoc)
   * 
   * @see kr.co.bsisys.fw.message.MessageAccessor#getMessage(java.lang.String)
   */
  @Override
  public String getMessage(String code) {
    return getMessage(code, null);
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see kr.co.bsisys.fw.message.MessageAccessor#getMessage(java.lang.String,
   * java.lang.Object[])
   */
  @Override
  public String getMessage(String code, Object[] args) {
    String message;
    
    if (args == null) {
      message = getMessageSourceAccessor().getMessage(code);
    } else {
      message = getMessageSourceAccessor().getMessage(code, args);
    }
    
    return message;
  }
  
}
