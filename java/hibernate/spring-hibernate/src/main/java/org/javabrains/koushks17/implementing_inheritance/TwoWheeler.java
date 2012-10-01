package org.javabrains.koushks17.implementing_inheritance;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class TwoWheeler extends Vehicle {

	private String steeringHandle;
	
}
