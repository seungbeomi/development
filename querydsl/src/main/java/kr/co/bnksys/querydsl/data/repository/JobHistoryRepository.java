package kr.co.bnksys.querydsl.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import kr.co.bnksys.querydsl.model.hr.JobHistory;
import kr.co.bnksys.querydsl.model.hr.JobHistoryPK;

public interface JobHistoryRepository extends JpaRepository<JobHistory, JobHistoryPK>, QuerydslPredicateExecutor<JobHistory> {

}
