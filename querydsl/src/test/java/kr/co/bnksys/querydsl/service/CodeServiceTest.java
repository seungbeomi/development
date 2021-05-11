package kr.co.bnksys.querydsl.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.bnksys.querydsl.model.first.User;

@SpringBootTest
class CodeServiceTest {

    @Autowired
    CodeService codeService;

    @Autowired
    UserService userService;

    @Test
    void test() {
        assertNotNull(codeService);
        assertNotNull(userService);

        codeService.findByCode("COM007");

        User user = userService.findByName("관리자");
        System.out.println(user);
    }

}
