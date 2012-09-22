package com.hibernate.chapter01;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name="EmployeeInfo")
public class Employee {

	@Id
	@TableGenerator(name="empid", 
		table="emppktb", pkColumnName="empkey", pkColumnValue="empvalue",
		allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="empid")
	@Column(name="EmployeeId")
	private int empId;
	private String empName;
	@Transient
	private String empPassword;
	@Column(nullable=false)
	private String empEmailAddress;
	@Basic
	private boolean isPermanent;
	@Temporal(TemporalType.DATE)
	private Calendar empJoinDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date empLoginTime;
	
	public Employee() {}
	public Employee(int empId, String empName) {
		this.empId = empId;
		this.empName = empName;
	}
	
}
