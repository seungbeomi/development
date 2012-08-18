package com.packtpub.springhibernate.ch06.component.annotation;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Phone {
	
	@Column(name = "COMMENT")
	private String comment;
	@Column(name = "NUMBER")
	private String number;
	
	public Phone() {}
	
	public Phone(String comment, String number) {
		this.comment = comment;
		this.number = number;
	}
	
}
