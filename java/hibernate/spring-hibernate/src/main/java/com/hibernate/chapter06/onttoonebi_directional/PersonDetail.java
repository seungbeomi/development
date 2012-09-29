package com.hibernate.chapter06.onttoonebi_directional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
	
	// One to One Bi-directional
	@OneToOne(mappedBy="personDetail", cascade=CascadeType.ALL)
	private Person person;
	
}
