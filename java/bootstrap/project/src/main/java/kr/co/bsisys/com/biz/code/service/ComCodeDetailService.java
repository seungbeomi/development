/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.com.biz.code.service;

import java.util.List;

import kr.co.bsisys.com.biz.code.vo.ComCodeDetail;

/**
 * 공통코드상세 Service 인터페이스
 * 
 * @since 2014. 5. 12.
 * @author IT지원/과장/손승범
 * @see egovframework.com.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService
 */
public interface ComCodeDetailService {
  
  /** 공통상세코드 조회 */
  List<ComCodeDetail> selectComCodeDetailList(ComCodeDetail comCodeDetail);
  
  /** 공통상세코드 상세조회 */
  ComCodeDetail selectComCodeDetailDetail(ComCodeDetail comCodeDetail);
  
  /** 공통상세코드 총갯수조회 */
  int selectComCodeDetailListTotCnt(ComCodeDetail comCodeDetail);
  
  /** 공통상세코드 등록 */
  void insertComCodeDetail(ComCodeDetail comCodeDetail);
  
  /** 공통상세코드 수정 */
  void updateComCodeDetail(ComCodeDetail comCodeDetail);
  
  /** 공통상세코드 삭제 */
  void deleteComCodeDetail(ComCodeDetail comCodeDetail);
  
}
