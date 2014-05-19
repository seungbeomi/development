/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.biz.vo;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import kr.co.bsisys.fw.vo.VO;

/**
 * 
 * @since 2014. 5. 6.
 * @author BS정보시스템/손승범
 */
@Entity
public class Dept extends VO {
  
  @Id
  private Long deptNo;
  private String dName;
  private String loc;
  
  @OneToMany
  @JoinColumn(name = "deptno", insertable = false, updatable = false)
  private Collection<Emp> empList = new LinkedHashSet<Emp>();
  
  public Long getDeptNo() {
    return deptNo;
  }
  
  public void setDeptNo(Long deptNo) {
    this.deptNo = deptNo;
  }
  
  public String getdName() {
    return dName;
  }
  
  public void setdName(String dName) {
    this.dName = dName;
  }
  
  public String getLoc() {
    return loc;
  }
  
  public void setLoc(String loc) {
    this.loc = loc;
  }
  
  public Collection<Emp> getEmpList() {
    return empList;
  }
  
  public void setEmpList(Collection<Emp> empList) {
    this.empList = empList;
  }
  
}
