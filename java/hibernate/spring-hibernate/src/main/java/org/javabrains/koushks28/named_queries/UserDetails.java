package org.javabrains.koushks28.named_queries;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@NamedQuery(name="UserDetails.byId", query="from UserDetails where userId = ?")
@NamedNativeQuery(
		name="UserDetails.byName", 
		query="SELECT * FROM USER_DETAILS WHERE USERNAME = ?",
		resultClass=UserDetails.class)
@Table(name="USER_DETAILS")
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	private String userName;
	
}
