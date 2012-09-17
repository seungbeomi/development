package com.packtpub.springhibernate.ch06.map.annotation;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.MapKey;

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
	
	@CollectionOfElements(targetElement = String.class)
	@JoinTable(
			name = "STUDENT_PAPER",
			joinColumns = @JoinColumn(name = "STUDENT_ID")
	)
	@MapKey(
			columns = @Column(name = "PAPER_TITLE")
	)
	@Column(name="PAPER_PATH")
	private Map<String, String> papers = new HashMap<String, String>();	// <map> element
	
}
