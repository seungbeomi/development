/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.util;

import kr.co.bsisys.fw.exception.ClassLoadException;

/**
 * 문자열로 부터 인스턴스를 생성하는 유틸리티 클래스.
 * 
 * @since 2013. 3. 16.
 * @author BS정보시스템/손승범
 */
public class ClassUtil {
  
  /**
   * 클래스명으로 인스턴스 생성
   * 
   * @param className
   * @return
   * @throws ClassLoadException
   */
  public static Object create(String className) throws ClassLoadException {
    
    Object object = null;
    
    Thread t = Thread.currentThread();
    ClassLoader cl = t.getContextClassLoader();
    
    try {
      object = cl.loadClass(className).newInstance();
    } catch (InstantiationException e) {
      throw new ClassLoadException(e);
    } catch (IllegalAccessException e) {
      throw new ClassLoadException(e);
    } catch (ClassNotFoundException e) {
      throw new ClassLoadException(e);
    }
    
    return object;
  }
  
  /**
   * 클래스명으로 클래스 생성
   * 
   * @param fullClassName
   * @return
   * @throws ClassNotFoundException
   */
  public static Class<?> getClass(String fullClassName) throws ClassNotFoundException {
    Class<?> clazz = Class.forName(fullClassName);
    return clazz;
  }
  
}
