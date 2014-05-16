/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw;

/**
 * <pre>
 * 시  스  템 : 공통 
 * 프로그램ID : Constants.java
 * 프로그램명 : 상수
 * 설      명 : 상수 인터페이스
 * 작  성  자 : BS정보시스템/손승범
 * 작  성  일 : 2013. 11. 20.
 * </pre>
 */
public interface Constants {
  
  /** OS 이름 */
  String OS_NAME = System.getProperty("os.name");
  
  /** 시스템구분자 */
  String SEPARATOR = "↕";
  
  /** 개행 */
  String NEW_LINE = System.getProperty("line.separator");
  
  String SESSION_DIR_BASE = "";
  int FILE_UPLOAD_BUFF_SIZE = 2 * 1024; // 2K
  
}
