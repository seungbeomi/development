package com.hibernate.chapter01;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TestEmployee {

	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(Employee.class);
		cfg.configure("hbm2ddl.xml");
		
		new SchemaExport(cfg).create(true, true);
		
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		Employee emp = new Employee(1, "seungbeomi");
		
		session.save(emp);
		
		session.getTransaction().commit();
	}
}
