package com.hibernate.chapter02.onclasstotwotable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class CustomerTest {

	@Test
	public void test() { 
		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(Customer.class);
		cfg.configure("hbm2ddl.xml");
		
		new SchemaExport(cfg).create(true, true);
		
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setCustomerName("seungbeomi");
		customer.setCustomerAddress("Busan");
		customer.setCreditScore(780);
		customer.setRewardPoints(12000);
		
		session.save(customer);
		
		session.getTransaction().commit();
	}
}
