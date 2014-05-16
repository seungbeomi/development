/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.web.view;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

/**
 * View resolver for returning JSON in a view-based system. Always returns a
 * {@link MappingJacksonJsonView}.
 * 
 * @since 2013. 7. 18.
 * @author BS정보시스템/손승범
 */
public class JsonViewResolver implements ViewResolver {

  /**
   * Get the view to use.
   * 
   * @return Always returns an instance of {@link MappingJacksonJsonView}.
   */
  @Override
  public View resolveViewName(String viewName, Locale locale) throws Exception {
    MappingJacksonJsonView view = new MappingJacksonJsonView();
    view.setPrettyPrint(true);
    return view;
  }

}
