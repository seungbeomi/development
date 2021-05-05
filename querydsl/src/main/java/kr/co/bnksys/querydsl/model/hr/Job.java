package kr.co.bnksys.querydsl.model.hr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "HR_JOBS")
@Entity
public class Job {

    @Id
    @Column(name = "JOB_ID", length = 10)
    private String jobId;

    @Column(name = "JOB_TITLE", length = 35)
    private String title;

    @Column(name = "MIN_SALARY")
    private Long minSalary;

    @Column(name = "MAX_SALARY")
    private Long maxSalary;

}
