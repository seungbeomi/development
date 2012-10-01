package org.javabrains.koushks18.implementing_inheritance_single_table_strategy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
@DiscriminatorValue("Car")
public class FourWheeler extends Vehicle {

	private String steeringWheel;
	
}
