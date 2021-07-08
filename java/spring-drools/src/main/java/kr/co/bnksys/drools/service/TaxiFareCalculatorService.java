package kr.co.bnksys.drools.service;

import kr.co.bnksys.drools.model.Fare;
import kr.co.bnksys.drools.model.TaxiRide;

public interface TaxiFareCalculatorService {


    Long calculateFare(TaxiRide taxiRide, Fare rideFare);
    
}
