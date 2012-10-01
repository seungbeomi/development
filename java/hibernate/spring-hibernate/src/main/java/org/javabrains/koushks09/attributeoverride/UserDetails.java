package org.javabrains.koushks09.attributeoverride;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="USER_DETAILS")
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	private String userName;
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="street",  column=@Column(name="HOME_STREET_NAME")),
		@AttributeOverride(name="city",    column=@Column(name="HOME_CITY_NAME")),
		@AttributeOverride(name="state",   column=@Column(name="HOME_STATE_NAME")),
		@AttributeOverride(name="pincode", column=@Column(name="HOME_PIN_CODE"))
	})
	private Address homeAddress;
	@Embedded
	private Address officeAddress;

}
