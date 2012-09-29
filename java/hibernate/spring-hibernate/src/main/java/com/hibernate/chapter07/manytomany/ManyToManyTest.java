package com.hibernate.chapter07.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class ManyToManyTest {

	@Test
	public void test() {
		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(Event.class);
		cfg.addAnnotatedClass(Delegate.class);
		cfg.configure("hbm2ddl.xml");

		new SchemaExport(cfg).create(true, true);

		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();

		{
			Delegate d1 = new Delegate();
			d1.setDelegateName("son");
			Delegate d2 = new Delegate();
			d2.setDelegateName("lee");
			Delegate d3 = new Delegate();
			d3.setDelegateName("kim");
			Delegate d4 = new Delegate();
			d4.setDelegateName("park");
			
			Event e1 = new Event();
			e1.setEventName("hibernate");
			Event e2 = new Event();
			e2.setEventName("spring");
			Event e3 = new Event();
			e3.setEventName("java");
			
			e1.getDelegates().add(d1);
			e1.getDelegates().add(d2);
			e1.getDelegates().add(d3);
			e2.getDelegates().add(d2);
			e2.getDelegates().add(d3);
			e3.getDelegates().add(d4);
			
			session.save(d1);
			session.save(d2);
			session.save(d3);
			session.save(d4);
			session.save(e1);
			session.save(e2);
			session.save(e3);
		}

		session.getTransaction().commit();

	}
	
}
