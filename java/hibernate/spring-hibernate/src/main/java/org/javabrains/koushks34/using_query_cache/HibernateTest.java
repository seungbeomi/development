package org.javabrains.koushks34.using_query_cache;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.javabrains.koushks30.understanding_restrictions.UserDetails;
import org.junit.Test;

public class HibernateTest {

	@Test
	public void test() {
		Configuration cfg = new Configuration();
		cfg.configure("hbm2ddl.xml");
		cfg.addAnnotatedClass(UserDetails.class);

		SessionFactory factory = cfg.buildSessionFactory();
		{
			Session session = factory.openSession();
			session.beginTransaction();
	
			UserDetails user = new UserDetails();
			user.setUserName("user");
			session.save(user);
	
			Query query = session.createQuery("from UserDetails user where user.userId = 1");
			query.setCacheable(true);
			List<UserDetails> users = query.list();
	
			session.getTransaction().commit();
			session.close();
		}
		{
			Session session = factory.openSession();
			session.beginTransaction();
			
			Query query = session.createQuery("from UserDetails user where user.userId = 1");
			query.setCacheable(true);
			List<UserDetails> users = query.list();
			
			session.getTransaction().commit();
			session.close();
		}

	}

}
