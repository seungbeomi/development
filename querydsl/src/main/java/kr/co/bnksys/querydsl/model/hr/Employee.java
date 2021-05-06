package kr.co.bnksys.querydsl.model.hr;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "HR_EMPLOYEES")
@Entity
public class Employee {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private Long id;

    @Column(name = "FIRST_NAME", length = 20)
    private String firstName;

    @Column(name = "LAST_NAME", length = 25)
    private String lastName;

    @Column(name = "EMAIL", length = 25)
    private String email;

    @Column(name = "PHONE_NUMBER", length = 20)
    private String phoneNumber;

    @Column(name = "HIRE_DATE")
    private LocalDate hireDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "JOB_ID")
    private Job job;

    @Column(name = "SALARY")
    private Long salary;

    @Column(name = "COMMISSION_PCT")
    private Double commissionPct;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MANAGER_ID")
    private Employee manager;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    public Employee(Long id, String firstName, String lastName, String email, String phoneNumber, LocalDate hireDate, Job job, Long salary, Double commissionPct, Department department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.job = job;
        this.salary = salary;
        this.commissionPct = commissionPct;
        this.department = department;
    }

}
