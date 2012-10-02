package org.javabrains.koushks24.persisting_detached_objects;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.javabrains.koushks22.transient_persistent_and_detached_objects.UserDetails;
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

		user = (UserDetails) session.get(UserDetails.class, 1);	// persistent object
		
		session.getTransaction().commit();
		session.close();
		
		user.setUserName("Updated Username after session close");	// detached object
		
		session = factory.openSession();
		session.beginTransaction();
		
		session.update(user);	// persistent object
		user.setUserName("Change username");
		
		session.getTransaction().commit();
		session.close();
		
		
	}

}
