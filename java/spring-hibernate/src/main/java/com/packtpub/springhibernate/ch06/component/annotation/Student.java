package com.packtpub.springhibernate.ch06.component.annotation;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="STUDENT")
@Data
public class Student {

	private int id;
	
	@Embedded
	private Phone phone;
	
}
