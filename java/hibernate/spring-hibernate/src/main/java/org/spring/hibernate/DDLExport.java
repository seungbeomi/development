package org.spring.hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.packtpub.springhibernate.ch06.list.annotation.Student;


public class DDLExport {

	public static void main(String[] args) {
		Configuration config = new Configuration();
		config.addAnnotatedClass(Student.class);
		config.configure("hbm2ddl.xml");	// hibernate.cfg.xml
		
		new SchemaExport(config).create(true, true);
	}
	
}
