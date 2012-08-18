package com.packtpub.springhibernate.ch06.list.annotation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.IndexColumn;

@Data
@Entity
@Table(name = "STUDENT")
public class Student {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@CollectionOfElements(targetElement=String.class)
	@JoinTable(
			name = "STUDENT_PAPER",
			joinColumns = @JoinColumn(name = "STUDENT_ID")
	)
	@IndexColumn(name="POSITION")
	@Column(name="PAPER_PATH")
	private List<String> papers = new ArrayList<String>();	// <list> element
	
}
