package org.javabrains.koushks15.mappedby;

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
			Vehicle vehicle2 = new Vehicle();
			vehicle2.setVehicleName("Jeep");
			
			user.getVehicles().add(vehicle);
			user.getVehicles().add(vehicle2);
			
			vehicle.setUser(user);
			vehicle2.setUser(user);
			
			session.save(user);
			session.save(vehicle);
			session.save(vehicle2);
		}
		
		session.getTransaction().commit();
		session.close();
		
	}
	
}
