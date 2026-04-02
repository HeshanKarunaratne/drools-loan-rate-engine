package com.example.drools;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
public class DroolsService {
    private final KieContainer kieContainer;

    public DroolsService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public Loan getRate(User applicantRequest) {
        Loan loan = new Loan();
        try (KieSession kieSession = kieContainer.newKieSession()) {
            kieSession.setGlobal("loan", loan);
            kieSession.insert(applicantRequest);
            kieSession.fireAllRules();
            kieSession.dispose();
        }
        return loan;
    }

}