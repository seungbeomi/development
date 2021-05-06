package kr.co.bnksys.querydsl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "TB_DEPTS")
@Entity
public class Dept {

    @Id
    @Column(name = "DEPT_ID")
    private String id;

    @Column(name = "DEPT_NAME")
    private String name;

}
