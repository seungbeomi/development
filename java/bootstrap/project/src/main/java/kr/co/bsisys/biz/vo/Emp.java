/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.biz.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import kr.co.bsisys.fw.vo.VO;

/**
 * 
 * @since 2014. 5. 1.
 * @author BS정보시스템/손승범
 */
@Entity
public class Emp extends VO {
  
  @Id
  private Long empNo;
  private String ename;
  private String job;
  private Integer mgr;
  private Date hiredate;
  private Double sal;
  private Double comm;
  private Integer deptNo;
  
  @ManyToOne
  @JoinColumn(name="deptno", insertable = false, updatable = false)
  private Dept dept;
  
  public Long getEmpNo() {
    return empNo;
  }
  
  public void setEmpNo(Long empNo) {
    this.empNo = empNo;
  }
  
  public String getEname() {
    return ename;
  }
  
  public void setEname(String ename) {
    this.ename = ename;
  }
  
  public String getJob() {
    return job;
  }
  
  public void setJob(String job) {
    this.job = job;
  }
  
  public Integer getMgr() {
    return mgr;
  }
  
  public void setMgr(Integer mgr) {
    this.mgr = mgr;
  }
  
  public Date getHiredate() {
    return hiredate;
  }
  
  public void setHiredate(Date hiredate) {
    this.hiredate = hiredate;
  }
  
  public Double getSal() {
    return sal;
  }
  
  public void setSal(Double sal) {
    this.sal = sal;
  }
  
  public Double getComm() {
    return comm;
  }
  
  public void setComm(Double comm) {
    this.comm = comm;
  }
  
  public Integer getDeptNo() {
    return deptNo;
  }
  
  public void setDeptNo(Integer deptNo) {
    this.deptNo = deptNo;
  }
  
  public Dept getDept() {
    return dept;
  }
  
  public void setDept(Dept dept) {
    this.dept = dept;
  }
  
}
