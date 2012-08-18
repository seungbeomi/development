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
@Table(name = "STUDENT")
public class Student {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@OneToOne
	@JoinColumn(name = "ID")
	private Phone phone;
	
	public Student() {}
	
	public Student(String firstName) {
		this.firstName = firstName;
	}
	
}
