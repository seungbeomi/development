package com.hibernate.chapter03;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class School {

	@Id
	@GeneratedValue
	private int schoolId;
	private String schoolName;
	
	@Embedded
	private SchoolDetail schoolDetail;
}
