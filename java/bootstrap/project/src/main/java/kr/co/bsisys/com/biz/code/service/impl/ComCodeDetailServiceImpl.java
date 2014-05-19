/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.com.biz.code.service.impl;

import java.util.List;

import javax.inject.Inject;

import kr.co.bsisys.com.biz.code.dao.ComCodeDetailDao;
import kr.co.bsisys.com.biz.code.service.ComCodeDetailService;
import kr.co.bsisys.com.biz.code.vo.ComCodeDetail;

import org.springframework.stereotype.Service;

/**
 * 공통코드상세 Service 클래스
 * 
 * @since 2014. 5. 12.
 * @author IT지원/과장/손승범
 * @see egovframework.com.sym.ccm.cde.service.impl.EgovCcmCmmnDetailCodeManageServiceImpl
 */
@Service
public class ComCodeDetailServiceImpl implements ComCodeDetailService {
  
  @Inject
  private ComCodeDetailDao comCodeDetailDao;
  
  /** 공통상세코드 조회 */
  @Override
  public List<ComCodeDetail> selectComCodeDetailList(ComCodeDetail comCodeDetail) {
    return comCodeDetailDao.selectComCodeDetailList(comCodeDetail);
  }
  
  /** 공통상세코드 상세조회 */
  @Override
  public ComCodeDetail selectComCodeDetailDetail(ComCodeDetail comCodeDetail) {
    return comCodeDetailDao.selectComCodeDetailDetail(comCodeDetail);
  }
  
  /** 공통상세코드 총갯수조회 */
  @Override
  public int selectComCodeDetailListTotCnt(ComCodeDetail comCodeDetail) {
    return comCodeDetailDao.selectComCodeDetailListTotCnt(comCodeDetail);
  }
  
  /** 공통상세코드 등록 */
  @Override
  public void insertComCodeDetail(ComCodeDetail comCodeDetail) {
    comCodeDetailDao.insertComCodeDetail(comCodeDetail);
  }
  
  /** 공통상세코드 수정 */
  @Override
  public void updateComCodeDetail(ComCodeDetail comCodeDetail) {
    comCodeDetailDao.updateComCodeDetail(comCodeDetail);
  }
  
  /** 공통상세코드 삭제 */
  @Override
  public void deleteComCodeDetail(ComCodeDetail comCodeDetail) {
    comCodeDetailDao.deleteComCodeDetail(comCodeDetail);
  }
  
}
