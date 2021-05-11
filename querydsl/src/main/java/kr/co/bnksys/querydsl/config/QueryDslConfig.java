package kr.co.bnksys.querydsl.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration
public class QueryDslConfig {

    @PersistenceContext(unitName = "first")
    @Qualifier(value = "firstEntityManagerFactory")
    private EntityManager firstEntityManager;

    @PersistenceContext(unitName = "second")
    @Qualifier(value = "secondEntityManagerFactory")
    private EntityManager secondEntityManager;

    @Bean
    public JPAQueryFactory firstJpaQueryFactory() {
        return new JPAQueryFactory(firstEntityManager);
    }

    @Bean
    public JPAQueryFactory secondJpaQueryFactory() {
        return new JPAQueryFactory(secondEntityManager);
    }

}
