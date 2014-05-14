/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.biz.svc;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import kr.co.bsisys.biz.vo.Dept;
import kr.co.bsisys.biz.vo.Emp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @since 2014. 5. 5.
 * @author BS정보시스템/손승범
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration({ "file:src/main/resources/META-INF/spring/context-*.xml" })
@ActiveProfiles({ "local", "jpa" })
public class EmpSvcTest {
  
  @Inject
  EmpSvc empSvc;
  
  @Test
  public void testEmpSvc() {
    List<Emp> list = empSvc.findAll();
    assertEquals(14, list.size());
    
    Emp emp = new Emp();
    emp.setEmpNo(9999L);
    emp.setEname("seungbeomi");
    emp.setJob("developer");
    emp.setMgr(7839);
    emp.setHiredate(new Date());
    emp.setSal(100.0);
    emp.setComm(1.23);
    emp.setDeptNo(10); 
    
    Emp inserted = empSvc.save(emp);
    assertEquals("seungbeomi", inserted.getEname());
    
    List<Emp> afterList = empSvc.findAll();
    assertEquals(15, afterList.size());
    
    emp = empSvc.findOne(9999);
    assertEquals("seungbeomi", emp.getEname());
    
    emp.setJob("AA");
    Emp updated = empSvc.save(emp);
    assertEquals("AA", updated.getJob());
    
    Dept dept = emp.getDept();
    System.out.println(dept.getdName());
    
  }
  
}
