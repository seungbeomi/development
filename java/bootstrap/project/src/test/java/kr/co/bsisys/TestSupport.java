/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys;

//import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

/**
 * 내용을 입력해주세요.
 * 
 * @since 2014. 5. 12.
 * @author IT지원/과장/손승범
 */
@Test
@Transactional
@ContextConfiguration({ "file:src/main/resources/META-INF/spring/context-*.xml" })
//@ActiveProfiles({ "local", "mybatis" })
//public class TestSupport extends AbstractTestNGSpringContextTests {
public class TestSupport extends AbstractTransactionalTestNGSpringContextTests {
  
}
