package com.packtpub.springhibernate.ch06.manytoone.xml;

import lombok.Data;

@Data
public class Student {

	private int id;
	private String name;
	private Address address;
	
	public Student() {}
	public Student(String name) {
		this.name = name;
	}
	
}
