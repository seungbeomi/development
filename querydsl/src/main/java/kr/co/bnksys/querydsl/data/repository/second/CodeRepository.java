package kr.co.bnksys.querydsl.data.repository.second;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import kr.co.bnksys.querydsl.model.second.Code;

public interface CodeRepository extends JpaRepository<Code, Long>, QuerydslPredicateExecutor<Code> {

    List<Code> findByCode(String code);

}
