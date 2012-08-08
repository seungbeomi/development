package com.packtpub.springhibernate.ch03;

import lombok.Data;

@Data
public class Student {

	private int id;
	private String firstName;
	private String lastName;
	
	public Student() {}
	
	public Student (String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
}
