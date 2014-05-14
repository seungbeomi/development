/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.com.biz.code.vo;

import kr.co.bsisys.fw.vo.VO;

/**
 * 공통코드 클래스
 * 
 * @since 2014. 5. 11.
 * @author BS정보시스템/손승범
 */
public class ComCode extends VO {
  
  private String codeId;   // 코드ID 
  private String codeIdNm; // 코드ID명
  private String codeIdDc; // 코드ID설명
  private char useAt;      // 사용여부
  private String clCode;   // 분류코드
  
  public String getCodeId() {
    return codeId;
  }
  
  public void setCodeId(String codeId) {
    this.codeId = codeId;
  }
  
  public String getCodeIdNm() {
    return codeIdNm;
  }
  
  public void setCodeIdNm(String codeIdNm) {
    this.codeIdNm = codeIdNm;
  }
  
  public String getCodeIdDc() {
    return codeIdDc;
  }
  
  public void setCodeIdDc(String codeIdDc) {
    this.codeIdDc = codeIdDc;
  }
  
  public char getUseAt() {
    return useAt;
  }
  
  public void setUseAt(char useAt) {
    this.useAt = useAt;
  }
  
  public String getClCode() {
    return clCode;
  }
  
  public void setClCode(String clCode) {
    this.clCode = clCode;
  }

}
