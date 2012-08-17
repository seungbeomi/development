package com.packtpub.springhibernate.ch06.onetoone.xml;

import lombok.Data;

@Data
public class Phone {

	private int id;
	private String comment;
	private String number;
	private Student student;
	
	public Phone() {}
	
	public Phone(String comment, String number) {
		this.comment = comment;
		this.number = number;
	}
	
}
