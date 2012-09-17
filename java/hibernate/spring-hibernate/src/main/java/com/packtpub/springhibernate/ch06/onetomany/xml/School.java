package com.packtpub.springhibernate.ch06.onetomany.xml;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class School {

	private int id;
	private String name;
	private List<Student> students = new ArrayList<Student>();
	
	public School() {}
	public School(String name) {
		this.name = name;
	}
	
}
