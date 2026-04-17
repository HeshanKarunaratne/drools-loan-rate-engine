package com.example.drools.strategy.core;

import com.example.drools.dto.RuleResult;
import com.example.drools.dto.User;

import java.util.List;

/**
 * @author Heshan Karunaratne
 */
public interface RuleEngineStrategy {
    List<RuleResult> execute(User user);
}
