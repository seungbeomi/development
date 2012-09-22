package com.hibernate.chapter01;

import java.sql.Date;
import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestEmployee {

	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(Employee.class);
		cfg.configure("hbm2ddl.xml");
		
		// new SchemaExport(cfg).create(true, true);
		
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		{
			Employee emp = new Employee();
			//emp.setEmpId(100);
			emp.setEmpName("seungbeomi");
			emp.setEmpEmailAddress("seungbeomi@gmail.com");
			emp.setEmpPassword("password");
			emp.setPermanent(true);
			emp.setEmpJoinDate(new GregorianCalendar(2012, 9, 22));
			emp.setEmpLoginTime(Date.valueOf("2010-09-21"));
			
			session.save(emp);
		}
		{
			Employee emp = new Employee();
			//emp.setEmpId(100);
			emp.setEmpName("test");
			emp.setEmpEmailAddress("test@gmail.com");
			emp.setEmpPassword("testpassword");
			emp.setPermanent(true);
			emp.setEmpJoinDate(new GregorianCalendar(2012, 9, 22));
			emp.setEmpLoginTime(Date.valueOf("2010-09-21"));
			
			session.save(emp);
		}
		
		session.getTransaction().commit();
	}
}
