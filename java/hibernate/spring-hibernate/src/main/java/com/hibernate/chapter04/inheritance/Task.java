package com.hibernate.chapter04.inheritance;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Task extends Module {

	private String taskName;
	
}
