/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.web.servlet.handler;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 세션의 내용을 체크하는 인터셉터 클래스
 * 
 * <pre>
 * - web application context 파일에 등록
 * 
 *   &lt;mvc:interceptors&gt;
 *     &lt;bean class="kr.co.bsisys.fw.web.servlet.handler.SessionChekerInterceptor" /&gt;
 *   &lt;/mvc:interceptors&gt;
 * </pre>
 * 
 * @since 2013. 10. 22.
 * @author BS정보시스템/손승범
 */
public class SessionChekerInterceptor extends HandlerInterceptorAdapter {
  
  private static final Logger logger = LoggerFactory.getLogger(SessionChekerInterceptor.class);
  
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    
    HttpSession session = request.getSession(false);
    
    if (session != null) {
      Enumeration<String> keys = session.getAttributeNames();
      while (keys.hasMoreElements()) {
        String key = keys.nextElement();
        if (logger.isDebugEnabled()) {
          logger.debug(" session => {} : {}", new Object[] { key, session.getAttribute(key) });
        }
      }
    }
    
    return super.preHandle(request, response, handler);
  }
  
}
