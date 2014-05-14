/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @since 2014. 5. 6.
 * @author BS정보시스템/손승범
 */
public class VO implements Serializable {
  
  private static final Logger logger = LoggerFactory.getLogger(VO.class);
  
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
  /** 등록자ID */
  private String createdbyId;
  /** 등록일 */
  private Date createddate;
  /** 수정자ID */
  private String lastmodifiedbyId;
  /** 수정일 */
  private Date lastmodifieddate;
  
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
  
  public String getCreatedbyId() {
    return createdbyId;
  }
  
  public void setCreatedbyId(String createdbyId) {
    this.createdbyId = createdbyId;
  }
  
  public Date getCreateddate() {
    return createddate;
  }
  
  public void setCreateddate(Date createddate) {
    this.createddate = createddate;
  }
  
  public String getLastmodifiedbyId() {
    return lastmodifiedbyId;
  }
  
  public void setLastmodifiedbyId(String lastmodifiedbyId) {
    this.lastmodifiedbyId = lastmodifiedbyId;
  }
  
  public Date getLastmodifieddate() {
    return lastmodifieddate;
  }
  
  public void setLastmodifieddate(Date lastmodifieddate) {
    this.lastmodifieddate = lastmodifieddate;
  }
  
  @Override
  public String toString() {
    if (logger.isDebugEnabled()) {
      return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    } else {
      return ToStringBuilder.reflectionToString(this);
    }
  }
  
}
