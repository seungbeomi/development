/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * @since 2014. 5. 6.
 * @author BS정보시스템/손승범
 */
public class VO implements Serializable {
  
  /** 검색조건 */
  private String searchCondition;
  /** 검색Keyword */
  private String searchKeyword;
  /** 검색사용여부 */
  private String searchUseYn;
  /** 현재페이지 */
  private int pageIndex = 1;
  /** 페이지갯수 */
  private int pageUnit = 10;
  /** 페이지사이즈 */
  private int pageSize = 10;
  /** firstIndex */
  private int firstIndex = 1;
  /** lastIndex */
  private int lastIndex = 1;
  /** recordCountPerPage */
  private int recordCountPerPage = 10;
  
  // AUDIT
  /** 등록일 */
  private Date frstRegistPnttm;
  /** 등록자ID */
  private String frstRegisterId;
  /** 수정일 */
  private Date lastUpdtPnttm;
  /** 수정자ID */
  private String lastUpdusrId;
  
  public String getSearchCondition() {
    return searchCondition;
  }
  
  public void setSearchCondition(String searchCondition) {
    this.searchCondition = searchCondition;
  }
  
  public String getSearchKeyword() {
    return searchKeyword;
  }
  
  public void setSearchKeyword(String searchKeyword) {
    this.searchKeyword = searchKeyword;
  }
  
  public String getSearchUseYn() {
    return searchUseYn;
  }
  
  public void setSearchUseYn(String searchUseYn) {
    this.searchUseYn = searchUseYn;
  }
  
  public int getPageIndex() {
    return pageIndex;
  }
  
  public void setPageIndex(int pageIndex) {
    this.pageIndex = pageIndex;
  }
  
  public int getPageUnit() {
    return pageUnit;
  }
  
  public void setPageUnit(int pageUnit) {
    this.pageUnit = pageUnit;
  }
  
  public int getPageSize() {
    return pageSize;
  }
  
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
  
  public int getFirstIndex() {
    return firstIndex;
  }
  
  public void setFirstIndex(int firstIndex) {
    this.firstIndex = firstIndex;
  }
  
  public int getLastIndex() {
    return lastIndex;
  }
  
  public void setLastIndex(int lastIndex) {
    this.lastIndex = lastIndex;
  }
  
  public int getRecordCountPerPage() {
    return recordCountPerPage;
  }
  
  public void setRecordCountPerPage(int recordCountPerPage) {
    this.recordCountPerPage = recordCountPerPage;
  }
  
  public Date getFrstRegistPnttm() {
    return frstRegistPnttm;
  }
  
  public void setFrstRegistPnttm(Date frstRegistPnttm) {
    this.frstRegistPnttm = frstRegistPnttm;
  }
  
  public String getFrstRegisterId() {
    return frstRegisterId;
  }
  
  public void setFrstRegisterId(String frstRegisterId) {
    this.frstRegisterId = frstRegisterId;
  }
  
  public Date getLastUpdtPnttm() {
    return lastUpdtPnttm;
  }
  
  public void setLastUpdtPnttm(Date lastUpdtPnttm) {
    this.lastUpdtPnttm = lastUpdtPnttm;
  }
  
  public String getLastUpdusrId() {
    return lastUpdusrId;
  }
  
  public void setLastUpdusrId(String lastUpdusrId) {
    this.lastUpdusrId = lastUpdusrId;
  }
  
  @Override
  public String toString() {
    //return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    return ToStringBuilder.reflectionToString(this);
  }
  
}
