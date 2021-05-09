package kr.co.bnksys.querydsl.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
@Entity
public class Employee {

    @Id
    @Column(name = "employee_id", length = 4)
    private Long id;

    @Column(name = "employee_name", length = 10)
    private String name;

    @Column(name = "job", length = 9)
    private String job;

    @OneToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @Column(name = "hiredate")
    private LocalDate hiredate;

    @Column(name = "salary", columnDefinition = "NUMBER(7,2)", precision = 7, scale = 2)
    private Double salary;

    @Column(name = "commission", columnDefinition = "NUMBER(7,2)", precision = 7, scale = 2)
    private Double commission;

    @OneToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
