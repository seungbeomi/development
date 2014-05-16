/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.vo;

import java.io.Serializable;

/**
 * <pre>
 * 시  스  템 : 공통
 * 프로그램ID : PagableVO.java
 * 프로그램명 : 페이지 정보 VO
 * 설      명 : 페이지 정보를 담기위한 VO 클래스
 * 작  성  자 : BS정보시스템/손승범
 * 작  성  일 : 2013. 11. 15.
 * </pre>
 * 
 * <pre>
 * // Pageable에 필수 정보를 넣어 준다.
 * Pageable pageable = new Pageable();
 * pageable.setCurrentPageNo(currentPageNo); //현재 페이지 번호
 * pageable.setRecordCountPerPage(10); //한 페이지에 게시되는 게시물 건수
 * pageable.setPageSize(10); //페이징 리스트의 사이즈
 * 
 * int firstRecordIndex = pageable.getFirstRecordIndex();
 * int recordCountPerPage = pageable.getRecordCountPerPage();
 * commandMap.put("firstIndex", firstRecordIndex );
 * commandMap.put("recordCountPerPage", recordCountPerPage );
 * 
 * List<Employee> employlist = employeeService.getAllEmployees(commandMap);
 * ...   
 * int employeeCount = employeeService.getEmployeeCount(commandMap);
 * pageable.setTotalRecordCount(employeeCount); //전체 게시물 건 수
 * 
 * //페이징 관련 정보가 있는 Pageable 객체를 모델에 반드시 넣어준다.
 * model.addAttribute("pageable", pageable);
 * return "employeelist";
 * </pre>
 */
public class Pageable implements Serializable {
  
  private static final long serialVersionUID = 4346230920092034805L;
  
  //REQUIRED FIELDS
  /** 현재페이지번호 */
  private int currentPageNo;
  /** 한 페이지당 게시되는 게시물 건 수 */
  private int recordCountPerPage;
  /** 페이지 리스트에 게시되는 페이지 건 수 */
  private int pageSize;
  /** 전체 게시물 건 수 */
  private int totalRecordCount;
  
  // NOT REQUIRED FIELDS
  // - 이 필드들은 REQUIRED FIELDS 값을 바탕으로 계산해서 정해지는 필드
  /** 페이지 개수 */
  private int totalPageCount;
  /** 페이지 리스트의 첫 페이지 번호 */
  private int firstPageNoOnPageList;
  /** 페이지 리스트의 마지막 페이지 번호 */
  private int lastPageNoOnPageList;
  /** 페이징 sql조건절에 사용되는 시작 rownum */
  private int firstRecordIndex;
  /** 페이징 sql조건절에 사용되는 마지막 rownum */
  private int lastRecordIndex;
  
  public Pageable() {
  }
  
  public Pageable(int currentPageNo, int recordCountPerPage, int pageSize) {
    this.currentPageNo = currentPageNo;
    this.recordCountPerPage = recordCountPerPage;
    this.pageSize = pageSize;
  }
  
  /** 현재페이지번호 */
  public int getCurrentPageNo() {
    return currentPageNo;
  }
  
  /** 현재페이지번호 */
  public void setCurrentPageNo(int currentPageNo) {
    this.currentPageNo = currentPageNo;
  }
  
  /** 한 페이지당 게시되는 게시물 건 수 */
  public int getRecordCountPerPage() {
    return recordCountPerPage;
  }
  
  /** 한 페이지당 게시되는 게시물 건 수 */
  public void setRecordCountPerPage(int recordCountPerPage) {
    this.recordCountPerPage = recordCountPerPage;
  }
  
  /** 한 페이지당 게시되는 게시물 건 수 */
  public int getPageSize() {
    return pageSize;
  }
  
  /** 한 페이지당 게시되는 게시물 건 수 */
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
  
  /** 전체 게시물 건 수 */
  public int getTotalRecordCount() {
    return totalRecordCount;
  }
  
  /** 전체 게시물 건 수 */
  public void setTotalRecordCount(int totalRecordCount) {
    this.totalRecordCount = totalRecordCount;
  }
  
  ///////////////////////////////////////////////////////////////////////////////
  /** 페이지 개수 */
  public int getTotalPageCount() {
    totalPageCount = ((getTotalRecordCount() - 1) / getRecordCountPerPage()) + 1;
    return totalPageCount;
  }
  
  /** 페이지 리스트의 첫 페이지 번호 */
  public int getFirstPageNo() {
    return 1;
  }
  
  /** 페이지 리스트의 마지막 페이지 번호 */
  public int getLastPageNo() {
    return getTotalPageCount();
  }
  
  /** 페이지 리스트의 첫 페이지 번호 */
  public int getFirstPageNoOnPageList() {
    firstPageNoOnPageList = ((getCurrentPageNo() - 1) / getPageSize()) * getPageSize() + 1;
    return firstPageNoOnPageList;
  }
  
  /** 페이지 리스트의 마지막 페이지 번호 */
  public int getLastPageNoOnPageList() {
    lastPageNoOnPageList = getFirstPageNoOnPageList() + getPageSize() - 1;
    if (lastPageNoOnPageList > getTotalPageCount()) {
      lastPageNoOnPageList = getTotalPageCount();
    }
    return lastPageNoOnPageList;
  }
  
  /** 페이징 sql조건절에 사용되는 시작 rownum */
  public int getFirstRecordIndex() {
    firstRecordIndex = (getCurrentPageNo() - 1) * getRecordCountPerPage();
    return firstRecordIndex;
  }
  
  /** 페이징 sql조건절에 사용되는 마지막 rownum */
  public int getLastRecordIndex() {
    lastRecordIndex = getCurrentPageNo() * getRecordCountPerPage();
    return lastRecordIndex;
  }
}
