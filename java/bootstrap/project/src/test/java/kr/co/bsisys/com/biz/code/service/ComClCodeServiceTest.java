/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.com.biz.code.service;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import kr.co.bsisys.TestSupport;
import kr.co.bsisys.com.biz.code.vo.ComClCode;
import kr.co.bsisys.fw.vo.Pageable;

import org.testng.annotations.Test;

/**
 * 공통분류코드 Service 테스트
 * 
 * @since 2014. 5. 13.
 * @author IT지원/과장/손승범
 */
public class ComClCodeServiceTest extends TestSupport {
  
  @Inject
  ComClCodeService comClCodeService;
  
  @Test
  public void testSelectComClCodeList() {
    
    ComClCode vo = new ComClCode();
    Pageable pageable = new Pageable(1, 10, 10);
    vo.setFirstIndex(pageable.getFirstRecordIndex());
    vo.setRecordCountPerPage(pageable.getRecordCountPerPage());
    // ---------------------------------------------------------------
    
    // 전체조회
    List<ComClCode> list = comClCodeService.selectComClCodeList(vo);
    assertEquals(1, list.size());
    
    list.clear();
    vo.setSearchCondition("1");
    vo.setSearchKeyword("EFC");
    list = comClCodeService.selectComClCodeList(vo);
    assertEquals("전자정부 프레임워크 공통서비스", list.get(0).getClCodeNm());
    
    list.clear();
    vo.setSearchCondition("2");
    vo.setSearchKeyword("전자정부");
    list = comClCodeService.selectComClCodeList(vo);
    assertEquals("전자정부 프레임워크 공통서비스", list.get(0).getClCodeNm());
    
  }
  
  @Test
  public void testCRUD() {
    
    // test data
    ComClCode vo = new ComClCode();
    vo.setClCode("SON");
    vo.setClCodeNm("seungbeomi");
    vo.setClCodeDc("bootstrap project");
    vo.setUseAt('Y');
    
    // cnt
    int cnt = comClCodeService.selectComClCodeListTotCnt(vo);
    assertEquals(1, cnt);
    
    // insert
    comClCodeService.insertComClCode(vo);
    cnt = comClCodeService.selectComClCodeListTotCnt(vo);
    assertEquals(2, cnt);
    
    // update
    vo.setClCodeNm("update");
    vo.setClCodeDc("update");
    vo.setLastUpdusrId("lastUpdusrId");
    comClCodeService.updateComClCode(vo);
    
    // select
    ComClCode updated = comClCodeService.selectComClCodeDetail(vo);
    assertNotNull(updated);
    assertEquals("update", updated.getClCodeNm());
    assertEquals("update", updated.getClCodeDc());
    
    // delete
    comClCodeService.deleteComClCode(vo);
    ComClCode deleted = comClCodeService.selectComClCodeDetail(vo);
    assertNotNull(deleted);
    assertEquals('N', deleted.getUseAt());
  }
  
}
