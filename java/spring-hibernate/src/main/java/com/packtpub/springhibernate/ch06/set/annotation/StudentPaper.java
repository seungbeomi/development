package com.packtpub.springhibernate.ch06.set.annotation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="STUDENT_PAPER")
public class StudentPaper {

	@Column(name="STUDENT_ID")
	private int studentId;
	
	@Column(name="PAPER_PATH")
	private String paperPath;
	
}
