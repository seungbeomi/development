package com.hibernate.chapter01;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Employee {

	@Id
	private int empId;
	private String empName;
	
	public Employee() {}
	public Employee(int empId, String empName) {
		this.empId = empId;
		this.empName = empName;
	}
	
}
