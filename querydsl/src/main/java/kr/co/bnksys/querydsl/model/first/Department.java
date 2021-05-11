package kr.co.bnksys.querydsl.model.first;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "departments")
@Entity
public class Department {

    @Id
    @Column(name = "department_id", length = 2)
    private Long id;

    @Column(name = "department_name", length = 14)
    private String name;

    @Column(name = "location", length = 13)
    private String location;

}
