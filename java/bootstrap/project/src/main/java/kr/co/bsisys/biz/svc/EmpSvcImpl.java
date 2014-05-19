/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.biz.svc;

import java.util.List;

import javax.annotation.Resource;

import kr.co.bsisys.biz.dao.EmpDao;
import kr.co.bsisys.biz.vo.Emp;

import org.springframework.stereotype.Service;

/**
 * 
 * @since 2014. 5. 1.
 * @author BS정보시스템/손승범
 */
@Service
public class EmpSvcImpl implements EmpSvc {
  
  @Resource(name="empDao")
  EmpDao empDao;
  
  //EmpRepo empRepo;
  
  public List<Emp> findAll() {
    //return empRepo.findAll();
    return empDao.selectList();
  }
  
  public Emp findOne(long empNo) {
    //return empRepo.findOne(empNo);
    return empDao.selectOne(empNo);
  }
  
  public Emp save(Emp emp) {
    empDao.insert(emp);
    //return empRepo.save(emp);
    return null;
  }
  
  public void delete(long empNo) {
    //empRepo.delete(empNo);
    empDao.delete(empNo);
  }
  
}
