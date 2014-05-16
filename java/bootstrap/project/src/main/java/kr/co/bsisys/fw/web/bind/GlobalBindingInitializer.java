/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.web.bind;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * 컨트롤러에서 공통적으로 사용할 프로퍼티 에디터 등록
 * 
 * @since 2014. 5. 9.
 * @author IT지원/과장/손승범
 */
public class GlobalBindingInitializer implements WebBindingInitializer {
  
  @Override
  public void initBinder(WebDataBinder binder, WebRequest request) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    dateFormat.setLenient(false);
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
  }
}
