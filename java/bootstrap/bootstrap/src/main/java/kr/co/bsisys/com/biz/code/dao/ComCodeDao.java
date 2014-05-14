/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.com.biz.code.dao;

import java.util.List;

import kr.co.bsisys.com.biz.code.vo.ComCode;

/**
 * 공통코드 Dao 인터페이스
 * 
 * @since 2014. 5. 11.
 * @author BS정보시스템/손승범
 */
public interface ComCodeDao {
  
  /** 공통코드 조회 */
  List<ComCode> selectComCodeList(ComCode comCode);
  
  /** 공통코드 상세 조회 */
  ComCode selectComCodeDetail(ComCode comCode);
  
  /** 공통코드 총갯수 조회 */
  int selectComCodeCnt(ComCode comCode);
  
  /** 공통코드 등록 */
  void insertComCode(ComCode comCode);
  
  /** 공통코드 수정 */
  void updateComCode(ComCode comCode);
  
  /** 공통코드 삭제 */
  void deleteComCode(ComCode comCode);
  
}
