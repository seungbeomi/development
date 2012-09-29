package com.hibernate.chapter05.onttoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {

	private Session session;
	
	@Before
	public void setUp() {
		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(Person.class);
		cfg.addAnnotatedClass(PersonDetail.class);
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
	public void test() {
		
		PersonDetail d = new PersonDetail();
		d.setZipCode("123456");
		d.setJob("Programmer");
		d.setIncome(123.456);
		
		Person p = new Person();
		p.setPersonName("seungbeomi");
		p.setPersonDetail(d);
		
		session.save(p);
		// no need if we set cascadeType
		// session.save(d);
	}

}
