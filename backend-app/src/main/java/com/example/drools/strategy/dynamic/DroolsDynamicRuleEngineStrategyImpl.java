package com.example.drools.strategy.dynamic;

import com.example.drools.dto.RuleResult;
import com.example.drools.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DroolsDynamicRuleEngineStrategyImpl {

    public DroolsDynamicRuleEngineStrategyImpl() {
    }

    public List<RuleResult> evaluate(User user, MultipartFile ruleFile) throws IOException {
        log.info("Starting DroolsDynamicRuleEngineStrategyImpl for user: {}", user);
        List<RuleResult> results = new ArrayList<>();
        String drl = new String(ruleFile.getBytes(), StandardCharsets.UTF_8);
        KieHelper kieHelper = new KieHelper();

        kieHelper.addContent(drl, ResourceType.DRL);

        var kieBase = kieHelper.build();
        KieSession session = kieBase.newKieSession();

        try {
            session.setGlobal("results", results);
            session.insert(user);
            session.fireAllRules();

        } finally {
            session.dispose();
        }

        return results;
    }
}