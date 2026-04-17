package com.example.drools.service;

import com.example.drools.dto.RuleResult;
import com.example.drools.dto.User;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DroolsRuleEngineStrategyImpl implements RuleEngineStrategy {
    private final KieContainer kieContainer;

    public DroolsRuleEngineStrategyImpl(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    @Override
    public List<RuleResult> execute(User user) {
        List<RuleResult> results = new ArrayList<>();
        try (KieSession kieSession = kieContainer.newKieSession()) {
            kieSession.setGlobal("results", results);
            kieSession.insert(user);
            kieSession.fireAllRules();
            kieSession.dispose();
        }
        return results;
    }
}