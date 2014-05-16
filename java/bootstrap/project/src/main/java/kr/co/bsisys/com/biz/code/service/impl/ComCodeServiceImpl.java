/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.com.biz.code.service.impl;

import java.util.List;

import javax.inject.Inject;

import kr.co.bsisys.com.biz.code.dao.ComCodeDao;
import kr.co.bsisys.com.biz.code.service.ComCodeService;
import kr.co.bsisys.com.biz.code.vo.ComCode;

import org.springframework.stereotype.Service;

/**
 * 공통코드 서비스 구현클래스
 * 
 * @since 2014. 5. 11.
 * @author BS정보시스템/손승범
 */
@Service
public class ComCodeServiceImpl implements ComCodeService {
  
  @Inject
  private ComCodeDao comCodeDao;
  
  /* (non-Javadoc)
   * @see kr.co.bsisys.com.biz.code.svc.ComCodeService#selectComCodeList(kr.co.bsisys.com.biz.code.vo.ComCode)
   */
  @Override
  public List<ComCode> selectComCodeList(ComCode comCode) {
    return comCodeDao.selectComCodeList(comCode);
  }
  
  @Override
  public ComCode selectComCodeDetail(ComCode comCode) {
    return comCodeDao.selectComCodeDetail(comCode);
  }
  
  @Override
  public int selectComCodeListTotCnt(ComCode comCode) {
    return comCodeDao.selectComCodeListTotCnt(comCode);
  }
  
  @Override
  public void insertComCode(ComCode comCode) {
    comCodeDao.insertComCode(comCode);
  }
  
  @Override
  public void updateComCode(ComCode comCode) {
    comCodeDao.updateComCode(comCode);
  }
  
  @Override
  public void deleteComCode(ComCode comCode) {
    comCodeDao.deleteComCode(comCode);
  }
  
}
