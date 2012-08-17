package com.packtpub.springhibernate.ch06.map.xml;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Student {

	private int id;
	private String firstName;
	private Map<String, String> papers = new HashMap<String, String>();	// <map> element
	
}
