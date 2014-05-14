/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.biz.dao;

import java.util.List;

import kr.co.bsisys.biz.vo.Emp;

import org.springframework.stereotype.Repository;

/**
 * for MyBatis
 * 
 * @since 2014. 5. 1.
 * @author BS정보시스템/손승범
 */
@Repository
public interface EmpDao {
  
  List<Emp> selectList();
  
  Emp selectOne(long empNo);
  
  void insert(Emp emp);
  
  void update(Emp emp);
  
  void delete(long empNo);
  
}
