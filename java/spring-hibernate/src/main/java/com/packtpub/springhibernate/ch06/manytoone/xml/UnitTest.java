package com.packtpub.springhibernate.ch06.manytoone.xml;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class UnitTest {

	@Test
	public void testCase () {
		SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addClass(Student.class)
			.addClass(Address.class)
			.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		// -------------------------------------------------------------------------------
		
		Student student = new Student("name");
		Address address = new Address("street", "city", "zipcode");
		student.setAddress(address);
		
		session.save(student);
		
		// -------------------------------------------------------------------------------
		tx.commit();
		session.close();
	}
	
}
