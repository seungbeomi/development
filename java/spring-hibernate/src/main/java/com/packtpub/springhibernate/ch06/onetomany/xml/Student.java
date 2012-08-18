package com.packtpub.springhibernate.ch06.onetomany.xml;

import lombok.Data;

@Data
public class Student {

	private int id;
	private String firstName;
	private School school;

	public Student() {}
	public Student(String firstName) {
		this.firstName = firstName;
	}
}
