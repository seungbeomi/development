package kr.co.bnksys.querydsl.data.repository.impl;

import static kr.co.bnksys.querydsl.model.QUser.user;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.bnksys.querydsl.data.repository.UserRepositoryCustom;
import kr.co.bnksys.querydsl.data.repository.output.UserOutput;
import kr.co.bnksys.querydsl.model.User;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory query;

    public User findByName(String name) {
        return query.selectFrom(user)
                .where(user.name.eq(name))
                .fetchOne();
    }

    public UserOutput findDtoByName(String name) {
        return query.select(Projections.fields(UserOutput.class,
                user.name))
                .from(user)
                .where(user.name.eq(name))
                .fetchOne();
    }

}
