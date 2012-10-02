package org.javabrains.koushks28.named_queries;

import java.util.List;

import org.hibernate.Query;
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

		for (int i=1; i <= 10; i++) {
			UserDetails user = new UserDetails();
			user.setUserName("user"+ i);
			session.save(user);
		}
		
		/*
		Query query = session.getNamedQuery("UserDetails.byId");
		query.setInteger(0, 2);
		*/
		Query query = session.getNamedQuery("UserDetails.byName");
		query.setString(0, "user9");
		
		List<UserDetails> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		for (UserDetails u : list) {
			System.out.println(u);
		}
		
	}

}
