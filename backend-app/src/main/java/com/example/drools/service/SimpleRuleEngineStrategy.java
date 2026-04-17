package com.example.drools.service;

import com.example.drools.dto.RuleResult;
import com.example.drools.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Heshan Karunaratne
 */
@Service("SIMPLE")
@Slf4j
public class SimpleRuleEngineStrategy implements RuleEngineStrategy {

    @Override
    public List<RuleResult> execute(User user) {
        log.info("Starting SimpleRuleEngineStrategy for user: {}", user);
        return List.of(
                new RuleResult("TEST", "APPROVED", 100, "TEST-RULE")
        );
    }
}