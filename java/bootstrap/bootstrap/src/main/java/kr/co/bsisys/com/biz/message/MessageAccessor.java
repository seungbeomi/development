/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.com.biz.message;

/**
 * 
 * @since 2013. 3. 16.
 * @author BS정보시스템/손승범
 */
public interface MessageAccessor {

	/**
	 * 코드를 이용하여 메세지를 반환.
	 * 
	 * @param code
	 * @return
	 */
	String getMessage(String code);

	/**
	 * 코드와 변수를 이용하여 메세지를 반환.
	 * 
	 * @param code
	 * @param args
	 * @return
	 */
	String getMessage(String code, Object[] args);

}
