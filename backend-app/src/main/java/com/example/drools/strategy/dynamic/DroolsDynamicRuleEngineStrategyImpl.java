package com.example.drools.strategy.dynamic;

import com.example.drools.dto.Group;
import com.example.drools.dto.RuleResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DroolsDynamicRuleEngineStrategyImpl {

    public List<RuleResult> evaluate(Group group, String drl) {
        log.info("Starting DroolsDynamicRuleEngineStrategyImpl for group: {}", group);
        List<RuleResult> results = new ArrayList<>();
        KieHelper kieHelper = new KieHelper();

        kieHelper.addContent(drl, ResourceType.DRL);

        var kieBase = kieHelper.build();
        KieSession session = kieBase.newKieSession();

        try {
            session.setGlobal("results", results);
            session.insert(group);
            session.fireAllRules();

        } finally {
            session.dispose();
        }

        return results;
    }
}