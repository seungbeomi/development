package org.javabrains.koushks27.understanding_parameter_binding_and_sql_injection;

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
		
		String minUserId = "5";
		String userName = "user10";
		
		/*
		Query query = session.createQuery("from UserDetails where userId > ? and userName = ?");
		query.setParameter(0, Integer.parseInt(minUserId));
		query.setParameter(1, userName);
		 */
		
		Query query = session.createQuery("from UserDetails where userId > :userId and userName = :userName");
		query.setInteger("userId", Integer.parseInt(minUserId));
		query.setString("userName", userName);
		
		List<UserDetails> list = query.list();
		session.getTransaction().commit();
		session.close();
		
		for (UserDetails u : list) {
			System.out.println(u);
		}
		
	}

}
