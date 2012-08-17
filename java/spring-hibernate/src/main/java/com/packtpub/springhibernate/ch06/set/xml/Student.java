package com.packtpub.springhibernate.ch06.set.xml;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class Student {

	private int id;
	private String firstName;
	private Set papers = new HashSet();
	
}
