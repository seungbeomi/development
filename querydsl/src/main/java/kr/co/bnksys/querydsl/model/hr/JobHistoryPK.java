package kr.co.bnksys.querydsl.model.hr;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Embeddable
public class JobHistoryPK implements Serializable {

    private static final long serialVersionUID = 4108382074104128642L;

    @Column(name = "EMPLORY_ID")
    private Long employeeId;

    @Column(name = "START_DATE")
    private LocalDate startDate;

}
