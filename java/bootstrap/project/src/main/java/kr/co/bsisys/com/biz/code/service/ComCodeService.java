/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.com.biz.code.service;

import java.util.List;

import kr.co.bsisys.com.biz.code.vo.ComCode;

/**
 * 공통코드 서비스 클래스
 * 
 * @since 2014. 5. 11.
 * @author BS정보시스템/손승범
 * @see egovframework.com.sym.ccm.cca.service.EgovCcmCmmnCodeManageService
 */
public interface ComCodeService {
  
  /** 공통코드 조회 */
  List<ComCode> selectComCodeList(ComCode comCode);
  
  /** 공통코드 상세 조회 */
  ComCode selectComCodeDetail(ComCode comCode);
  
  /** 공통코드 총갯수 조회 */
  int selectComCodeListTotCnt(ComCode comCode);
  
  /** 공통코드 등록 */
  void insertComCode(ComCode comCode);
  
  /** 공통코드 수정 */
  void updateComCode(ComCode comCode);
  
  /** 공통코드 삭제 */
  void deleteComCode(ComCode comCode);
  
}
