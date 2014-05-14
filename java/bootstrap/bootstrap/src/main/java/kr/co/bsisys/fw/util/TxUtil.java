/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.util;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * <pre>
 * 시  스  템 : 공통
 * 프로그램ID : TxUtil.java
 * 프로그램명 : 
 * 설      명 : 
 * 작  성  자 : BS정보시스템/손승범
 * 작  성  일 : 2013. 12. 4.
 * </pre>
 */
public class TxUtil {
  
  public static void flush() {
    TransactionAspectSupport.currentTransactionStatus().flush();
  }
  
  public static void rollback() {
    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
  }
  
}
