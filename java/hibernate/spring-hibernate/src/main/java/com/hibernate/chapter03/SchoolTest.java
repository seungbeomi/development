package com.hibernate.chapter03;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class SchoolTest {

	@Test
	public void test() {
		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(School.class);
		cfg.configure("hbm2ddl.xml");

		new SchemaExport(cfg).create(true, true);

		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();

		SchoolDetail schoolDetail = new SchoolDetail();
		schoolDetail.setPublicSchool(false);
		schoolDetail.setSchoolAddress("Busan");
		schoolDetail.setStudentCount(300);

		School school = new School();
		school.setSchoolName("Suyoung High School");
		school.setSchoolDetail(schoolDetail);

		session.save(school);

		session.getTransaction().commit();

	}
}
