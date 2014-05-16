/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.com.biz.code.vo;

import kr.co.bsisys.fw.vo.VO;

/**
 * 공통코드 상세 클래스
 * 
 * @since 2014. 5. 11.
 * @author BS정보시스템/손승범
 * @see
 */
public class ComCodeDetail extends VO {
  
  private String codeId; // 코드ID
  private String code;   // 코드
  private String codeNm; // 코드명
  private String codeDc; // 코드설명
  private String sort;   // 정렬
  private char useAt;    // 사용여부
  
  public String getCodeId() {
    return codeId;
  }
  
  public void setCodeId(String codeId) {
    this.codeId = codeId;
  }
  
  public String getCode() {
    return code;
  }
  
  public void setCode(String code) {
    this.code = code;
  }
  
  public String getCodeNm() {
    return codeNm;
  }
  
  public void setCodeNm(String codeNm) {
    this.codeNm = codeNm;
  }
  
  public String getCodeDc() {
    return codeDc;
  }
  
  public void setCodeDc(String codeDc) {
    this.codeDc = codeDc;
  }
  
  public String getSort() {
    return sort;
  }
  
  public void setSort(String sort) {
    this.sort = sort;
  }
  
  public char getUseAt() {
    return useAt;
  }
  
  public void setUseAt(char useAt) {
    this.useAt = useAt;
  }
  
}
