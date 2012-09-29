package com.hibernate.chapter05.onttoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class PersonDetail {

	@Id
	@GeneratedValue
	@Column(name="detailId_PK")
	private int personDetailId;
	private String zipCode;
	private String job;
	private double income;
	
}
