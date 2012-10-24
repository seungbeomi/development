package kr.co.tsb.comp.batch.admin.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import kr.co.tsb.comp.batch.admin.domain.QrtzJobDetailsPK;
import kr.co.tsb.comp.batch.admin.domain.QrtzJobDetailsVO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Transactional
public class QrtzJobDetailsRepositoryTest {

	@Autowired QrtzJobDetailsRepository qrtzJobDetailsRepository;
	//@Autowired SessionFactory sessionFactory;
	
	@Test
	public void test() {
		assertNotNull(qrtzJobDetailsRepository);
		//assertNotNull(sessionFactory);
		
		QrtzJobDetailsPK pk = new QrtzJobDetailsPK("pilotJob","DEFAULT");
		
		// Read
		QrtzJobDetailsVO actural = qrtzJobDetailsRepository.findOne(pk);

		assertThat(actural.getJobClassName(), is("com.pcs.schedule.jobs.PersistentJob"));
		assertThat(actural.getIsStateful(), is("1"));
		
		List<QrtzJobDetailsVO> list = qrtzJobDetailsRepository.findAll();
		
		for (QrtzJobDetailsVO vo : list) {
			System.out.println(">>> " + vo);
		}
	}

}
