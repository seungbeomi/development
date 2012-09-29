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
public class Event {

	@Id
	@GeneratedValue
	private int eventId;
	private String eventName;
	
	@ManyToMany
	@JoinTable(name="JOIN_DELEGATE_EVENT", 
			joinColumns={ @JoinColumn(name="eventId") },
			inverseJoinColumns={ @JoinColumn(name="delegateId") })
	private List<Delegate> delegates = new ArrayList<Delegate>();
	
}
