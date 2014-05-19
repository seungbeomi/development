/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.com.biz.code.service;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import kr.co.bsisys.TestSupport;
import kr.co.bsisys.com.biz.code.vo.ComCodeDetail;
import kr.co.bsisys.fw.vo.Pageable;

import org.mybatis.spring.SqlSessionTemplate;
import org.testng.annotations.Test;

/**
 * 공통코드상세 Service 테스트
 * 
 * @since 2014. 5. 12.
 * @author IT지원/과장/손승범
 * @see kr.co.bsisys.com.biz.code.service.ComCodeDetailService
 */
public class ComCodeDetailServiceTest extends TestSupport {
  
  @Inject
  ComCodeDetailService comCodeDetailService;
  
  @Inject
  SqlSessionTemplate sqlSessionTemplate;
  
  @Test
  public void testSelectComCodeDetailList() {
    ComCodeDetail vo = new ComCodeDetail();
    
    Pageable pageable = new Pageable();
    pageable.setCurrentPageNo(1);
    pageable.setRecordCountPerPage(10);
    pageable.setPageSize(10);
    
    vo.setFirstIndex(pageable.getFirstRecordIndex());
    vo.setRecordCountPerPage(pageable.getRecordCountPerPage());
    
    // ------------------------------------------------------------
    vo.setSearchCondition("1");
    vo.setSearchKeyword("COM001");
    List<ComCodeDetail> resultList = comCodeDetailService.selectComCodeDetailList(vo);
    assertEquals(7, resultList.size());
    
    resultList.clear();
    vo.setSearchCondition("2");
    vo.setSearchKeyword("HIST");
    resultList = comCodeDetailService.selectComCodeDetailList(vo);
    assertEquals(5, resultList.size());
    
    resultList.clear();
    vo.setSearchCondition("3");
    vo.setSearchKeyword("일반게시판");
    resultList = comCodeDetailService.selectComCodeDetailList(vo);
    assertEquals(2, resultList.size());
  }
  
  @Test()
  public void testCRUD() {
    
    // test data
    ComCodeDetail vo = new ComCodeDetail();
    vo.setCodeId("COM001");
    vo.setCode("code");
    vo.setCodeNm("codeNm");
    vo.setCodeDc("codeDc");
    vo.setUseAt('Y');
    vo.setFrstRegisterId("frstRegisterId");
    
    // cnt
    int cnt = comCodeDetailService.selectComCodeDetailListTotCnt(vo);
    assertEquals(413, cnt);
    
    // insert
    comCodeDetailService.insertComCodeDetail(vo);
    
    // cnt
    cnt = comCodeDetailService.selectComCodeDetailListTotCnt(vo);
    assertEquals(414, cnt);
    
    // update
    vo.setCodeNm("update");
    vo.setCodeDc("update");
    vo.setLastUpdusrId("update");
    comCodeDetailService.updateComCodeDetail(vo);
    
    // sqlSessionTemplate.flushStatements();
    
    // select
    ComCodeDetail updated = comCodeDetailService.selectComCodeDetailDetail(vo);
    assertNotNull(updated);
    assertEquals("update", updated.getCodeNm());
    assertEquals("update", updated.getCodeDc());
    
    // delete
    comCodeDetailService.deleteComCodeDetail(vo);
    ComCodeDetail deleted = comCodeDetailService.selectComCodeDetailDetail(vo);
    assertNotNull(deleted);
    assertEquals('N', deleted.getUseAt());
    
  }
  
}
