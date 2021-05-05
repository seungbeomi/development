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
@Table(name = "HR_LOCATIONS")
@Entity
public class Location {

    @Id
    @Column(name = "LOCATION_ID")
    private Long id;

    @Column(name = "STREET_ADDRESS", length = 40)
    private String streetAddress;

    @Column(name = "POSTAL_CODE", length = 12)
    private String POSTAL_CODE;

    @Column(name = "CITY", length = 30)
    private String CITY;

    @Column(name = "STATE_PROVINCE", length = 25)
    private String STATE_PROVINCE;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;

}
