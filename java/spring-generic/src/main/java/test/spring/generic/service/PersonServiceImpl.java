package test.spring.generic.service;

import org.springframework.stereotype.Service;

import test.spring.generic.core.GenericServiceImpl;
import test.spring.generic.domain.Person;

@Service
public class PersonServiceImpl extends GenericServiceImpl<Person> implements PersonService {

}
