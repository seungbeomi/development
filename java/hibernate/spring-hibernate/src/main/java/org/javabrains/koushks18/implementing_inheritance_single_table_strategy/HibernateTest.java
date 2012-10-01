package org.javabrains.koushks18.implementing_inheritance_single_table_strategy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateTest {

	@Test
	public void test() {
		Configuration cfg = new Configuration();
		cfg.configure("hbm2ddl.xml");
		cfg.addAnnotatedClass(Vehicle.class);
		cfg.addAnnotatedClass(TwoWheeler.class);
		cfg.addAnnotatedClass(FourWheeler.class);
		
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		
		{
			Vehicle vehicle = new Vehicle();
			vehicle.setVehicleName("Car");
			
			TwoWheeler bike = new TwoWheeler();
			bike.setVehicleName("Bike");
			bike.setSteeringHandle("Bike Steering Handle");
			
			FourWheeler car = new FourWheeler();
			car.setVehicleName("Porsche");
			car.setSteeringWheel("Porsche Steering Wheel");
			
			session.save(vehicle);
			session.save(bike);
			session.save(car);
		}
		
		session.getTransaction().commit();
		session.close();
		
	}
	
}
