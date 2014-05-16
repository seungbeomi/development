/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * 
 * @since 2013. 10. 25.
 * @author BS정보시스템/손승범
 */
public class MessageUtil {
  
  /**
   * 메시지 반환
   * 
   * @param messageSourceAccessor
   * @param result
   * @return
   */
  public static List<String> getMessage(MessageSourceAccessor messageSourceAccessor, BindingResult result) {
    return getMessage(messageSourceAccessor, result, null);
  }
  
  /**
   * 메시지반환
   * 
   * @param messageSourceAccessor
   * @param result
   * @param args
   * @return
   */
  public static List<String> getMessage(MessageSourceAccessor messageSourceAccessor, BindingResult result, Object[] args) {
    
    List<String> messages = new ArrayList<String>();
    
    if (result.hasErrors()) {
      for (ObjectError err : result.getAllErrors()) {
        for (String code : err.getCodes()) {
          if (StringUtil.isNotEmpty(code)) {
            String message = "";
            try {
              message = messageSourceAccessor.getMessage(code, args);
              // 메시지가 존재하는 경우
              if (StringUtil.hasText(message)) {
                messages.add(message);
              }
            } catch (NoSuchMessageException e) {
              // do nothing
            }
          }
        }
      }
    }
    
    return messages;
  }
  
}
