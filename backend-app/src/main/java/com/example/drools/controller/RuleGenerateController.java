package com.example.drools.controller;

import com.example.drools.dto.RuleRequest;
import com.example.drools.dto.RuleResult;
import com.example.drools.generator.DroolsRuleGenerator;
import com.example.drools.strategy.dynamic.DroolsDynamicRuleEngineStrategyImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Heshan Karunaratne
 */
@RestController()
@RequestMapping("/rules")
@RequiredArgsConstructor
public class RuleGenerateController {

    private final DroolsDynamicRuleEngineStrategyImpl strategy;
    private final DroolsRuleGenerator generator;

    @PostMapping("/generate")
    public ResponseEntity<List<RuleResult>> generate(@RequestBody RuleRequest request) throws Exception {

        String drl = generator.generate(request);
        List<RuleResult> results = strategy.evaluate(request.getGroup(), drl);

        return ResponseEntity.ok(results);
    }
}
