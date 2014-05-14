/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @since 2013. 3. 17.
 * @author BS정보시스템/손승범
 */
public class RequestUtil {

	public static String getPathInfo(ServletRequest request) {
		if (request == null) {
			return null;
		}
		
		return ((HttpServletRequest) request).getRequestURI().replaceFirst(((HttpServletRequest) request).getContextPath(), "");
	}

}
