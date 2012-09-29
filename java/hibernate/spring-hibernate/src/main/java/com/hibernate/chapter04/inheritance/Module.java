package com.hibernate.chapter04.inheritance;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Module extends Project {

	private String moduleName;
	
}
