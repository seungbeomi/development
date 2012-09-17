package com.packtpub.springhibernate.ch06.component.annotation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class UnitTest {

	@Test
	public void testComponent () {
		SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Student.class)
			.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		// --------------------------------------------------------
		
		Student student = new Student();	// entity type
		Phone phone = new Phone("seungbeomi", "000-111-2222"); // value type
		student.setPhone(phone);
		
		session.save(student);
		
		// --------------------------------------------------------
		tx.commit();
		session.close();
	}
}
