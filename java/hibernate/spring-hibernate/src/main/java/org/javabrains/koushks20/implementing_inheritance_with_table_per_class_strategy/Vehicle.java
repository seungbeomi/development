package org.javabrains.koushks20.implementing_inheritance_with_table_per_class_strategy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Data;

@Data
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Vehicle {

	@Id
	@GeneratedValue
	private int vehicleId;
	private String vehicleName;
	
}
