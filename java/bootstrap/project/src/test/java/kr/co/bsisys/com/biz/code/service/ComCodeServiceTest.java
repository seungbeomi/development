/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.com.biz.code.service;

import static org.testng.AssertJUnit.assertEquals;

import java.util.List;

import javax.inject.Inject;

import kr.co.bsisys.TestSupport;
import kr.co.bsisys.com.biz.code.vo.ComCode;
import kr.co.bsisys.fw.vo.Pageable;

import org.testng.annotations.Test;

/**
 * 공통코드 서비스 테스트
 * 
 * @since 2014. 5. 11.
 * @author BS정보시스템/손승범
 */
public class ComCodeServiceTest extends TestSupport {
  
  @Inject
  ComCodeService comCodeService;
  
  @Test()
  public void testSelectComCodeList() {
    ComCode comCode = new ComCode();
    List<ComCode> resultList = null;
    
    Pageable pageable = new Pageable();
    pageable.setCurrentPageNo(1);
    pageable.setRecordCountPerPage(10);
    pageable.setPageSize(10);
    
    comCode.setFirstIndex(pageable.getFirstRecordIndex());
    comCode.setRecordCountPerPage(pageable.getRecordCountPerPage());
    
    comCode.setSearchCondition("1");
    comCode.setSearchKeyword("COM001");
    resultList = comCodeService.selectComCodeList(comCode);
    assertEquals(1, resultList.size());
    
    comCode.setSearchCondition("2");
    comCode.setSearchKeyword("등록");
    resultList = comCodeService.selectComCodeList(comCode);
    assertEquals(1, resultList.size());
    
    comCode.setSearchCondition("clCode");
    comCode.setSearchKeyword("EFC");
    resultList = comCodeService.selectComCodeList(comCode);
    assertEquals(10, resultList.size());
    
    // Cache Test
    // resultList = comCodeService.selectComCodeList(comCode);
    // assertEquals(10, resultList.size());
  }
  
  @Test
  public void testSelectComCodeDetail() {
    ComCode comCode = new ComCode();
    comCode.setCodeId("COM001");
    
    ComCode result = comCodeService.selectComCodeDetail(comCode);
    assertEquals("등록구분", result.getCodeIdNm());
    assertEquals("게시판, 커뮤니티, 동호회 등록구분코드", result.getCodeIdDc());
  }
  
  @Test
  public void testselectComCodeListTotCnt() {
    ComCode comCode = new ComCode();
    int cnt = comCodeService.selectComCodeListTotCnt(comCode);
    assertEquals(75, cnt);
    
    comCode.setSearchCondition("1");
    comCode.setSearchKeyword("COM001");
    cnt = comCodeService.selectComCodeListTotCnt(comCode);
    assertEquals(1, cnt);
    
    comCode.setSearchCondition("2");
    comCode.setSearchKeyword("DBMS");
    cnt = comCodeService.selectComCodeListTotCnt(comCode);
    assertEquals(1, cnt);
  }
  
  @Test
  public void testCUDComCode() {
    ComCode comCode = new ComCode();
    int cnt = comCodeService.selectComCodeListTotCnt(comCode);
    assertEquals(75, cnt);
    
    // insert
    comCode.setCodeId("codeId");
    comCode.setCodeIdNm("codeIdNm");
    comCode.setCodeIdDc("codeIdDc");
    comCode.setClCode("EFC");
    comCode.setUseAt('Y');
    comCode.setFrstRegisterId("frstRegisterId");
    comCodeService.insertComCode(comCode);
    
    cnt = comCodeService.selectComCodeListTotCnt(comCode);
    assertEquals(76, cnt);
    
    // update
    comCode.setCodeIdNm("update");
    comCode.setCodeIdDc("update");
    comCode.setLastUpdusrId("lastUpdusrId");
    comCodeService.updateComCode(comCode);
    
    ComCode updated = comCodeService.selectComCodeDetail(comCode);
    assertEquals("update", updated.getCodeIdNm());
    assertEquals("update", updated.getCodeIdDc());
    
    // delete
    comCodeService.deleteComCode(comCode);
    ComCode deleted = comCodeService.selectComCodeDetail(comCode);
    assertEquals('N', deleted.getUseAt());
  }
  
}
