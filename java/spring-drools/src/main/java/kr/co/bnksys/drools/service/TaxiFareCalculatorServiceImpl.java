package kr.co.bnksys.drools.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import kr.co.bnksys.drools.model.Fare;
import kr.co.bnksys.drools.model.TaxiRide;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TaxiFareCalculatorServiceImpl implements TaxiFareCalculatorService {

    private final KieContainer kieContainer;
    private final KieSession kieSession;

    @Override
    public Long calculateFare(TaxiRide taxiRide, Fare rideFare) {
        // KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("rideFare", rideFare);
        kieSession.insert(taxiRide);
        kieSession.fireAllRules();
        // kieSession.dispose();
        System.out.println("!! RIDE FARE !! " + rideFare.getTotalFare());
        return rideFare.getTotalFare();
    }
}
