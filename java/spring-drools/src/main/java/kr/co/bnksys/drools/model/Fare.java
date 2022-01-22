package kr.co.bnksys.drools.model;

import lombok.Data;

@Data
public class Fare {

    private Long nightSurcharge;
    private Long rideFare;

    public Long getTotalFare() {
        System.out.println("TOTAL FARE: " + nightSurcharge + " + " + rideFare);
        return nightSurcharge + rideFare;
    }
}
