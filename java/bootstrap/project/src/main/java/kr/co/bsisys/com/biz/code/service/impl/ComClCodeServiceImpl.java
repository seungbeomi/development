/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.com.biz.code.service.impl;

import java.util.List;

import javax.inject.Inject;

import kr.co.bsisys.com.biz.code.dao.ComClCodeDao;
import kr.co.bsisys.com.biz.code.service.ComClCodeService;
import kr.co.bsisys.com.biz.code.vo.ComClCode;

import org.springframework.stereotype.Service;

/**
 * 공통분류코드 Service 클래스
 * 
 * @since 2014. 5. 12.
 * @author IT지원/과장/손승범
 * @see egovframework.com.sym.ccm.ccc.service.impl.EgovCcmCmmnClCodeManageServiceImpl
 */
@Service
public class ComClCodeServiceImpl implements ComClCodeService {
  
  @Inject
  private ComClCodeDao comClCodeDao;
  
  /** 공통분류코드 조회 */
  @Override
  public List<ComClCode> selectComClCodeList(ComClCode comClCode) {
    return comClCodeDao.selectComClCodeList(comClCode);
  }
  
  /** 공통분류코드 상세조회 */
  @Override
  public ComClCode selectComClCodeDetail(ComClCode comClCode) {
    return comClCodeDao.selectComClCodeDetail(comClCode);
  }
  
  /** 공통분류코드 총갯수 조회 */
  @Override
  public int selectComClCodeListTotCnt(ComClCode comClCode) {
    return comClCodeDao.selectComClCodeListTotCnt(comClCode);
  }
  
  /** 공통분류코드 등록 */
  @Override
  public void insertComClCode(ComClCode comClCode) {
    comClCodeDao.insertComClCode(comClCode);
  }
  
  /** 공통분류코드 수정 */
  @Override
  public void updateComClCode(ComClCode comClCode) {
    comClCodeDao.updateComClCode(comClCode);
  }
  
  /** 공통분류코드 삭제 */
  @Override
  public void deleteComClCode(ComClCode comClCode) {
    comClCodeDao.deleteComClCode(comClCode);
  }
  
}
