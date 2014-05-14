/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.com.biz.code.svc;

import java.util.List;

import kr.co.bsisys.com.biz.code.vo.ComClCode;

/**
 * 공통분류코드 Service 인터페이스
 * 
 * @since 2014. 5. 12.
 * @author IT지원/과장/손승범
 * @see egovframework.com.sym.ccm.ccc.service.EgovCcmComClCodeManageService
 */
public interface ComClCodeService {
  
  /** 공통분류코드 조회 */
  List<ComClCode> selectComClCodeList(ComClCode comClCode);
  
  /** 공통분류코드 상세조회 */
  ComClCode selectComClCodeDetail(ComClCode comClCode);
  
  /** 공통분류코드 총갯수 조회 */
  int selectComClCodeListTotCnt(ComClCode comClCode);
  
  /** 공통분류코드 등록 */
  void insertComClCode(ComClCode comClCode);
  
  /** 공통분류코드 수정 */
  void updateComClCode(ComClCode comClCode);
  
  /** 공통분류코드 삭제 */
  void deleteComClCode(ComClCode comClCode);
  
}
