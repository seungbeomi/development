package org.javabrains.koushks11.configuration_collections_and_adding_keys;

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
		
		{
			UserDetails user = new UserDetails();
			user.setUserName("First User");
			
			Address home = new Address();
			home.setStreet("Street Name");
			home.setCity("City Name");
			home.setState("State Name");
			home.setPincode("Pincode Name");
			
			Address office = new Address();
			office.setStreet("Street Name2");
			office.setCity("City Name2");
			office.setState("State Name2");
			office.setPincode("Pincode Name2");
			
			user.getListOfAddresses().add(home);
			user.getListOfAddresses().add(office);
			
			session.save(user);
		}
		
		session.getTransaction().commit();
		session.close();
		
	}
	
}
