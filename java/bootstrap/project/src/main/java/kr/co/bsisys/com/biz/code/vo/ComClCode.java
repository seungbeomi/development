/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.com.biz.code.vo;

import kr.co.bsisys.fw.vo.VO;

/**
 * 공통분류코드 VO
 * 
 * @since 2014. 5. 12.
 * @author IT지원/과장/손승범
 */
public class ComClCode extends VO {
  
  /** 분류코드 */
  private String clCode;
  /** 분류코드명 */
  private String clCodeNm;
  /** 분류코드설명 */
  private String clCodeDc;
  /** 사용여부 */
  private char useAt;
  
  public String getClCode() {
    return clCode;
  }
  
  public void setClCode(String clCode) {
    this.clCode = clCode;
  }
  
  public String getClCodeNm() {
    return clCodeNm;
  }
  
  public void setClCodeNm(String clCodeNm) {
    this.clCodeNm = clCodeNm;
  }
  
  public String getClCodeDc() {
    return clCodeDc;
  }
  
  public void setClCodeDc(String clCodeDc) {
    this.clCodeDc = clCodeDc;
  }
  
  public char getUseAt() {
    return useAt;
  }
  
  public void setUseAt(char useAt) {
    this.useAt = useAt;
  }
  
}
