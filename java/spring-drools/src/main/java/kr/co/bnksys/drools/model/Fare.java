package kr.co.bnksys.drools.model;

import lombok.Data;

@Data
public class Fare {

    private Long nightSurcharge;
    private Long rideFare;
    
    public Long getTotalFare() {
        return nightSurcharge + rideFare;
    }
}
