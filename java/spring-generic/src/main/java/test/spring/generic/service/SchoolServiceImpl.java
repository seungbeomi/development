package test.spring.generic.service;

import org.springframework.stereotype.Service;

import test.spring.generic.core.GenericServiceImpl;
import test.spring.generic.domain.School;

@Service
public class SchoolServiceImpl extends GenericServiceImpl<School> implements SchoolService {

}
