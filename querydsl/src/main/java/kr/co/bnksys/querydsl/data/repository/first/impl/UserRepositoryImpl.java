package kr.co.bnksys.querydsl.data.repository.first.impl;

import static kr.co.bnksys.querydsl.model.QUser.user;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.bnksys.querydsl.data.repository.first.UserRepositoryCustom;
import kr.co.bnksys.querydsl.data.repository.first.output.UserOutput;
import kr.co.bnksys.querydsl.model.first.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory firstJpaQueryFactory;

    public User findByName(String name) {
        return firstJpaQueryFactory.selectFrom(user)
                .where(user.name.eq(name))
                .fetchOne();
    }

    public UserOutput findDtoByName(String name) {
        return firstJpaQueryFactory.select(Projections.fields(UserOutput.class,
                user.name))
                .from(user)
                .where(user.name.eq(name))
                .fetchOne();
    }

}
