package kr.co.bnksys.querydsl.model.hr;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "HR_COUNTRIES")
@Entity
public class Country {

    @Id
    @Column(name = "COUNTRY_ID", columnDefinition = "CHAR(2)")
    private String countryId;

    @Column(name = "COUNTRY_NAME", length = 40)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "REGION_ID")
    private Region region;
}
