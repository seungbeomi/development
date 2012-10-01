package org.javabrains.koushks19.implementing_inheritance_with_joined_strategy;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class TwoWheeler extends Vehicle {

	private String steeringHandle;
	
}
