package kr.co.bnksys.querydsl.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.bnksys.querydsl.model.QUser;
import kr.co.bnksys.querydsl.model.first.User;

// @Transactional
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    JPAQueryFactory queryFactory;

    //@Autowired
    //UserRepository userRepository;

    @BeforeEach
    public void setup() {}

    @SuppressWarnings("deprecation")
    @Test
    void testCreate() {
        //assertNotNull(userRepository);
        em.persist(User.builder()
                .name("seungbeomi")
                .build());
        em.flush();
        //}

        //@Test
        //void testFindByName() {
        assertNotNull(queryFactory);

        List<User> users = queryFactory
                .selectFrom(QUser.user)
                .fetch();

        assertNotNull(users);
        assertThat("seungbeomi", is(users.get(0).getName()));

    }

}
