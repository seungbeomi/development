package org.javabrains.koushks22.transient_persistent_and_detached_objects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateTest {

	@Test
	public void test() {
		Configuration cfg = new Configuration();
		cfg.configure("hbm2ddl.xml");
		cfg.addAnnotatedClass(UserDetails.class);

		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();

		UserDetails user = new UserDetails();
		user.setUserName("seungbeomi"); // transient object
		session.save(user); // persistent object
		user.setUserName("Updated User"); // persistent object
		user.setUserName("Updated User Again"); // persistent object 
												// update의 최종내용만 반영
		session.getTransaction().commit();
		session.close();
		
		user.setUserName("Detached Object");
	}

}
