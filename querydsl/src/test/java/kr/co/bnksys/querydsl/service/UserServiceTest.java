package kr.co.bnksys.querydsl.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.bnksys.querydsl.model.User;

@Transactional
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void test() {
        assertNotNull(userService);

        userService.save(User.builder()
                .id(15L)
                .name("22222")
                .fstRgDtti(LocalDateTime.now())
                .ltChDtti(LocalDateTime.now())
                .build());

        User user = userService.findByName("44444");
        assertNotNull(user);
    }

}
