package com.hibernate.chapter03;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class SchoolDetail {

	private String schoolAddress;
	private boolean isPublicSchool;
	private int studentCount;
	
}
