package com.packtpub.springhibernate.ch06.idbag.xml;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Data;

@Data
public class Student {

	private int id;
	private String firstName;
	private Collection<String> papers = new ArrayList<String>();	// <idbag> element
	
}
