package kr.co.bnksys.querydsl.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import kr.co.bnksys.querydsl.model.hr.Job;

public interface JobRepository extends JpaRepository<Job, String>, QuerydslPredicateExecutor<Job> {

}
