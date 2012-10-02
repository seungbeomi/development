package org.javabrains.koushks21.crud_operations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class HibernateTest {

	private Session session;

	@Before
	public void setUp() {
		Configuration cfg = new Configuration();
		cfg.configure("hbm2ddl.xml");
		cfg.addAnnotatedClass(UserDetails.class);

		SessionFactory factory = cfg.buildSessionFactory();
		session = factory.openSession();
		session.beginTransaction();
	}

	@After
	public void tearDown() {
		session.getTransaction().commit();
		session.close();
	}

	@Ignore @Test
	public void population() {
		for (int i = 1; i <= 10; i++) {
			UserDetails user = new UserDetails();
			user.setUserName("user" + i);
			session.save(user);
		}
	}
	
	@Ignore @Test
	public void delete() {
		UserDetails user = (UserDetails) session.get(UserDetails.class, 6);
		session.delete(user);
	}
	
	@Test
	public void update() {
		UserDetails user = (UserDetails) session.get(UserDetails.class, 5);
		user.setUserName("Update user");
		session.update(user);
	}

}
