package com.packtpub.springhibernate.ch06.onetoone.annotation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PHONE")
public class Phone {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	@Column(name = "COMMENT")
	private String comment;
	@Column(name = "NUMBER")
	private String number;
	
	@OneToOne
	@JoinColumn(name="ID")
	private Student student;
	
	public Phone() {}
	
	public Phone(String comment, String number) {
		this.comment = comment;
		this.number = number;
	}
	
}
