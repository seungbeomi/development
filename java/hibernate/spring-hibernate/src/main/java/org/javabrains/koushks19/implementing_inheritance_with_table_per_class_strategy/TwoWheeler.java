package org.javabrains.koushks19.implementing_inheritance_with_table_per_class_strategy;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class TwoWheeler extends Vehicle {

	private String steeringHandle;
	
}
