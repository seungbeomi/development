package kr.co.bnksys.querydsl.model;

import javax.persistence.CascadeType;
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
@Table(name = "TB_USERS")
@Entity
public class User {

    @Id
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DEPT_ID")
    private Dept dept;

}
