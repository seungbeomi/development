package kr.co.bnksys.querydsl.data.repository.first;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import kr.co.bnksys.querydsl.model.first.User;

public interface UserRepository
        extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User>, UserRepositoryCustom {

}
