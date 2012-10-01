package org.javabrains.koushks15.many_to_many_mapping;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Vehicle {

	@Id
	@GeneratedValue
	private int vehicleId;
	private String vehicleName;
	
	@ManyToMany(mappedBy="vehicles") // mappedBy 속성으로 USER_DETAILS_Vehicle 테이블에 매핑됨 
	private Collection<UserDetails> users = new ArrayList<UserDetails>();
	
}
