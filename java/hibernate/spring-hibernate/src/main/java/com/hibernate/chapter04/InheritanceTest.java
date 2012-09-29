package com.hibernate.chapter04;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InheritanceTest {

	// Single table (default)
	// Joined
	// Table per class
	
	Session session;
	
	@Before
	public void setUp() {
		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(Project.class);
		cfg.addAnnotatedClass(Module.class);
		cfg.addAnnotatedClass(Task.class);
		cfg.configure("hbm2ddl.xml");

		new SchemaExport(cfg).create(true, true);

		SessionFactory factory = cfg.buildSessionFactory();
		session = factory.getCurrentSession();
		session.beginTransaction();
	}
	
	@After
	public void teardown() {
		session.getTransaction().commit();
	}
	
	@Test
	public void singleTable() {
		Project p = new Project();
		p.setProjectName("Hibernate");
		
		Module m = new Module();
		m.setProjectName("Spring");
		m.setModuleName("AOP");
		
		Task t = new Task();
		t.setProjectName("Java");
		t.setModuleName("Collections");
		t.setTaskName("ArrayList");
		
		session.save(p);
		session.save(m);
		session.save(t);
	}
	
}
