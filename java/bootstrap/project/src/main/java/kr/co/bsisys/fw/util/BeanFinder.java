package kr.co.bsisys.fw.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * 시  스  템 : 공통 
 * 프로그램ID : BeanFinder.java
 * 프로그램명 : 스프링 빈 반환
 * 설      명 : 스프링 빈을 반환해주는 유틸 클래스
 * 작  성  자 : BS정보시스템/손승범
 * 작  성  일 : 2013. 11. 15.
 * </pre>
 */
@Component
public class BeanFinder implements ApplicationContextAware {
  
  // WebApplicationContext
  
  static GenericApplicationContext[] contextList = null;
  
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    if (contextList == null) {
      contextList = new GenericApplicationContext[1];
      contextList[0] = (GenericApplicationContext) applicationContext;
    } else {
      GenericApplicationContext[] newContextList = new GenericApplicationContext[contextList.length + 1];
      System.arraycopy(contextList, 0, newContextList, 0, contextList.length);
      newContextList[contextList.length] = (GenericApplicationContext) applicationContext;
      contextList = newContextList;
    }
  }
  
  public static String[] getBeanDefinitionNames() {
    
    if (contextList.length == 0) {
      return null;
    }
    
    if (contextList.length == 1) {
      return contextList[0].getBeanDefinitionNames();
    }
    
    String[] beanNameList = contextList[0].getBeanDefinitionNames();
    for (int i = 1; i < contextList.length; i++) {
      String[] tempNameList = contextList[i].getBeanDefinitionNames();
      if ((tempNameList == null) || (tempNameList.length == 0))
        continue;
      String[] aggregatedList = new String[beanNameList.length + tempNameList.length];
      System.arraycopy(beanNameList, 0, aggregatedList, 0, beanNameList.length);
      System.arraycopy(tempNameList, 0, aggregatedList, beanNameList.length, tempNameList.length);
      beanNameList = aggregatedList;
    }
    return beanNameList;
  }
  
  public static String[] getBeanNamesForType(Class<?> clazz) {
    
    if (contextList.length == 0) {
      return null;
    }
    
    if (contextList.length == 1) {
      return contextList[0].getBeanNamesForType(clazz);
    }
    
    String[] beanNameList = contextList[0].getBeanNamesForType(clazz);
    for (int i = 1; i < contextList.length; i++) {
      String[] tempNameList = contextList[i].getBeanNamesForType(clazz);
      if ((tempNameList == null) || (tempNameList.length == 0))
        continue;
      String[] aggregatedList = new String[beanNameList.length + tempNameList.length];
      System.arraycopy(beanNameList, 0, aggregatedList, 0, beanNameList.length);
      System.arraycopy(tempNameList, 0, aggregatedList, beanNameList.length, tempNameList.length);
      beanNameList = aggregatedList;
    }
    return beanNameList;
  }
  
  public static Object getBean(String name) {
    for (GenericApplicationContext context : contextList) {
      try {
        Object target = context.getBean(name);
        if (target != null)
          return target;
      } catch (Exception e) {
      }
    }
    return null;
  }
  
  public static Object getBean(Class<?> clazz) {
    for (GenericApplicationContext context : contextList) {
      String[] beanNameList = context.getBeanNamesForType(clazz);
      if ((beanNameList != null) && (beanNameList.length != 0))
        return context.getBean(beanNameList[0]);
    }
    return null;
  }
  
  /*
  public static ServletContext getServletContext() {
    if ((contextList == null) || (contextList.length == 0))
      return null;
    return contextList[0].getServletContext();
  }
  */
}
