/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.biz.svc;

import java.util.List;

import kr.co.bsisys.biz.vo.Emp;

/**
 * 
 * @since 2014. 5. 5.
 * @author BS정보시스템/손승범
 */
public interface EmpSvc {
  
  public List<Emp> findAll();

  public Emp findOne(long empNo);

  public Emp save(Emp emp);

  public void delete(long empNo);
  
}
