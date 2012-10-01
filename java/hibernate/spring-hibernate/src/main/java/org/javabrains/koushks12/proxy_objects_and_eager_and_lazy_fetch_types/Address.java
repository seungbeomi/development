package org.javabrains.koushks12.proxy_objects_and_eager_and_lazy_fetch_types;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {

	@Column(name="STREET_NAME")
	private String street;
	@Column(name="CITY_NAME")
	private String city;
	@Column(name="STATE_NAME")
	private String state;
	@Column(name="PIN_NAME")
	private String pincode;
	
}
