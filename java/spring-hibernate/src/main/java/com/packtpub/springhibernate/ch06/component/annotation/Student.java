package com.packtpub.springhibernate.ch06.component.annotation;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="STUDENT")
public class Student {

	@Id
	@GeneratedValue
	private int id;
	
	@Embedded
	private Phone phone;
	
}
