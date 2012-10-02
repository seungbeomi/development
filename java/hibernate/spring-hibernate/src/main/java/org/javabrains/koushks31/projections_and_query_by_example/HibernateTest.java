package org.javabrains.koushks31.projections_and_query_by_example;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.javabrains.koushks30.understanding_restrictions.UserDetails;
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
		
		UserDetails exampleUser = new UserDetails();
		//exampleUser.setUserId(5);
		exampleUser.setUserName("user5");
		
		Example example = Example.create(exampleUser);
		
		/*
		Criteria criteria = session.createCriteria(UserDetails.class)
				.addOrder(Order.desc("userId"));
		*/
		Criteria criteria = session.createCriteria(UserDetails.class)
							.add(example);
		
		List<UserDetails> list = criteria.list();
		session.getTransaction().commit();
		session.close();
		
		for (UserDetails u : list) {
			System.out.println(u);
		}
		
	}

}
