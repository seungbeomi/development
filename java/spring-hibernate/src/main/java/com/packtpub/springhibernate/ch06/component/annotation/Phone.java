package com.packtpub.springhibernate.ch06.component.annotation;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
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
