package kr.co.tsb.comp.batch.admin.dao;

import kr.co.tsb.comp.batch.admin.domain.QrtzJobDetailsPK;
import kr.co.tsb.comp.batch.admin.domain.QrtzJobDetailsVO;
import egov.data.hibernate.repository.HibernateRepository;

public interface QrtzJobDetailsRepository extends HibernateRepository<QrtzJobDetailsVO, QrtzJobDetailsPK> {

}
