package com.packtpub.springhibernate.ch06.component.xml;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class UnitTest {

	@Test
	public void testComponent () {
		SessionFactory factory = new Configuration()
			.configure("/com/packtpub/springhibernate/ch06/hibernate.cfg.xml")
			.addClass(Student.class)
			//.addResource("com/packtpub/springhibernate/ch06/component/xml/Student.hbm.xml")
			.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		Student student = new Student();	// entity type
		Phone phone = new Phone("seungbeomi", "000-111-2222"); // value type
		student.setPhone(phone);
		
		session.save(student);
		
		tx.commit();
		session.close();
	}
}
