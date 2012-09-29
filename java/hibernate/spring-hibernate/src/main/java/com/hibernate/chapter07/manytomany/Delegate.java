package com.hibernate.chapter07.manytomany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Delegate {

	@Id
	@GeneratedValue
	private int delegateId;
	private String delegateName;
	
	@ManyToMany
	@JoinTable(name="JOIN_DELEGATE_EVENT", 
			joinColumns={ @JoinColumn(name="delegateId") },
			inverseJoinColumns={ @JoinColumn(name="eventId") })
	private List<Event> events = new ArrayList<Event>();
	
}
