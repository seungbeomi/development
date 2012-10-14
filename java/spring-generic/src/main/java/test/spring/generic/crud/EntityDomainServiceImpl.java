package test.spring.generic.crud;

import org.dom4j.Entity;
import org.springframework.stereotype.Service;

@Service
public class EntityDomainServiceImpl extends DomainServiceImpl<Entity> implements EntityDomainService {

}
