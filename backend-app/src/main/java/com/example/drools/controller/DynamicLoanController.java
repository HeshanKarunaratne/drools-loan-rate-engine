package com.example.drools.controller;

import com.example.drools.dto.RuleResult;
import com.example.drools.dto.User;
import com.example.drools.strategy.dynamic.DroolsDynamicRuleEngineStrategyImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController()
@RequestMapping("/loan/dynamic")
public class DynamicLoanController {

    private final DroolsDynamicRuleEngineStrategyImpl droolsDynamicRuleEngineStrategy;

    public DynamicLoanController(DroolsDynamicRuleEngineStrategyImpl droolsDynamicRuleEngineStrategy) {
        this.droolsDynamicRuleEngineStrategy = droolsDynamicRuleEngineStrategy;
    }

    @PostMapping(value = "/result", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<List<RuleResult>> getResult(@RequestPart("user") User user,
                                                      @RequestPart("ruleFile") MultipartFile ruleFile) throws IOException {
        return ResponseEntity.ok(droolsDynamicRuleEngineStrategy.evaluate(user, ruleFile));
    }

}