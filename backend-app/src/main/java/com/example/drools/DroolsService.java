package com.example.drools;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DroolsService {
    private final KieContainer kieContainer;

    public DroolsService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public List<RuleResult> getResults(User applicantRequest) {
        List<RuleResult> results = new ArrayList<>();
        try (KieSession kieSession = kieContainer.newKieSession()) {
            kieSession.setGlobal("results", results);
            kieSession.insert(applicantRequest);
            kieSession.fireAllRules();
            kieSession.dispose();
        }
        return results;
    }

}