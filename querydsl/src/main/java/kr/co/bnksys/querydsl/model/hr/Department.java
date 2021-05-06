package kr.co.bnksys.querydsl.model.hr;

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
@Table(name = "HR_DEPARTMENTS")
@Entity
public class Department {

    @Id
    @Column(name = "DEPARTMENT_ID")
    private Long id;

    @Column(name = "DEPARTMENT_NAME", length = 30)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MANAGER_ID")
    private Employee manager;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    public Department(Long id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

}
