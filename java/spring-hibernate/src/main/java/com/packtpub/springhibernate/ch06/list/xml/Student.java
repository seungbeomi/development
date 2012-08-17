package com.packtpub.springhibernate.ch06.list.xml;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Student {

	private int id;
	private String firstName;
	private List<String> papers = new ArrayList<String>();	// <list> element
	
}
