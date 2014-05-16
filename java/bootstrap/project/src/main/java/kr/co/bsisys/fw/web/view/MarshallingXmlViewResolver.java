/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.web.view;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;

/**
 * View resolver for returning XML in a view-based system. Always returns a
 * {@link MarshallingView}.
 * 
 * @since 2013. 7. 18.
 * @author BS정보시스템/손승범 
 */
public class MarshallingXmlViewResolver implements ViewResolver {

  private Marshaller marshaller;

  @Autowired
  public MarshallingXmlViewResolver(Marshaller marshaller) {
    this.marshaller = marshaller;
  }

  /**
   * Get the view to use.
   * 
   * @return Always returns an instance of {@link MappingJacksonJsonView}.
   */
  @Override
  public View resolveViewName(String viewName, Locale locale) throws Exception {
    MarshallingView view = new MarshallingView();
    view.setMarshaller(marshaller);
    return view;
  }

}