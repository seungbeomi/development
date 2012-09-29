package com.hibernate.chapter04.inheritance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Data;

@Data
@Entity
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE) // default 한테이
//@Inheritance(strategy=InheritanceType.JOINED)	// 각 클래스의 속성만 각자의 테이블에 저장  
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)	// 상속받은 속성 전부 각자의 테이블에 저장 
public class Project {

	@Id
	@GeneratedValue
	private int projectId;
	private String projectName;
	
}
