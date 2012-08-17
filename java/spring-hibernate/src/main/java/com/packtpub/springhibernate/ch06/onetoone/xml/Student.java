package com.packtpub.springhibernate.ch06.onetoone.xml;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="STUDENT")
@Data
public class Student {

	private int id;
	private String firstName;
	private Phone phone;
	
	public Student() {}
	
	public Student(String firstName) {
		this.firstName = firstName;
	}
	
}
