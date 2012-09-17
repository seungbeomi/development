package com.packtpub.springhibernate.ch06.manytoone.xml;

import lombok.Data;

@Data
public class Address {

	private int id;
	private String street;
	private String city;
	private String zipcode;
	
	public Address() {}
	public Address(String street, String city, String zipcode) {
		this.street = street;
		this.city = city;
		this.zipcode = zipcode;
	}
	
}
