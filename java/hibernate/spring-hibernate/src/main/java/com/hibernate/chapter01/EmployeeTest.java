package com.hibernate.chapter01;

import java.util.Date;
import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class EmployeeTest {

	@Test
	public void test() {
		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(Employee.class);
		cfg.configure("hbm2ddl.xml");
		
		new SchemaExport(cfg).create(true, true);
		
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		{
			Employee emp = new Employee();
			emp.setEmpName("seungbeomi");
			emp.setEmpPassword("password");
			emp.setEmpEmailAddress("seungbeomi@gmail.com");
			emp.setPermanent(true);
			emp.setEmpJoinDate(new GregorianCalendar(2012, 9, 30));
			emp.setEmpLoginTime(new Date());
			
			session.save(emp);
		}
		session.getTransaction().commit();
	}
	
}
