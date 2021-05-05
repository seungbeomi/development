package kr.co.bnksys.querydsl.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import kr.co.bnksys.querydsl.model.hr.Employee;

public interface EmploymentRepository extends JpaRepository<Employee, Long>, QuerydslPredicateExecutor<Employee> {

}
