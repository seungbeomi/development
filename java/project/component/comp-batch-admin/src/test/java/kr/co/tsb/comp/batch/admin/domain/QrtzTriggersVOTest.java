package kr.co.tsb.comp.batch.admin.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class QrtzTriggersVOTest {

	@Test
	public void testComponent() {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		new SchemaExport(cfg).create(true, false);

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();

		// --------------------------------------------

		// --------------------------------------------
		session.getTransaction().commit();
		session.close();

		/*
		 * SessionFactory factory = new Configuration()
		 * .configure("hibernate.cfg.xml") .buildSessionFactory(); Session
		 * session = factory.openSession();
		 */
		// --------------------------------------------------------
		/*
		 * Criteria criteria = session.createCriteria(QrtzTriggersVO.class);
		 * criteria.add(Restrictions.idEq(new
		 * QrtzTriggersPK("pilotTrigger","DEFAULT"))); List<QrtzTriggersVO>
		 * result = criteria.list();
		 * 
		 * for (QrtzTriggersVO vo : result) { //System.out.println(">>> " + vo);
		 * System.out.println(">>> " + vo.getQrtzJobDetailsVO()); }
		 */
		/*
		 * Criteria criteria = session.createCriteria(QrtzJobDetailsVO.class);
		 * criteria.add(Restrictions.idEq(new
		 * QrtzJobDetailsPK("pilotJob","DEFAULT"))); List<QrtzJobDetailsVO>
		 * result = criteria.list();
		 * 
		 * for (QrtzJobDetailsVO vo : result) { System.out.println(">>> " + vo);
		 * } // --------------------------------------------------------
		 * 
		 * session.close();
		 */
	}

}
