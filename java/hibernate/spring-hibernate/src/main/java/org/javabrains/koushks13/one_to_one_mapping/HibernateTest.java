package org.javabrains.koushks13.one_to_one_mapping;

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
		cfg.addAnnotatedClass(Vehicle.class);
		
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		
		{
			UserDetails user = new UserDetails();
			user.setUserName("First User");
			
			Vehicle vehicle = new Vehicle();
			vehicle.setVehicleName("Car");
			
			user.setVehicle(vehicle);
			
			session.save(user);
			session.save(vehicle);
		}
		
		session.getTransaction().commit();
		session.close();
		
	}
	
}
