package com.packtpub.springhibernate.ch06.component.xml;

import lombok.Data;

@Data
public class Phone {

	private String comment;
	private String number;
	
	public Phone() {}
	
	public Phone(String comment, String number) {
		this.comment = comment;
		this.number = number;
	}
	
}
