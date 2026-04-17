package com.example.drools.controller;

import com.example.drools.dto.RuleDefinition;
import com.example.drools.dto.RuleResult;
import com.example.drools.dto.User;
import com.example.drools.service.DroolsRuleEngineStrategyImpl;
import org.drools.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController()
@RequestMapping("/loan")
public class LoanController {
    private final DroolsRuleEngineStrategyImpl droolsRuleEngineStrategyImpl;

    public LoanController(DroolsRuleEngineStrategyImpl droolsRuleEngineStrategyImpl) {
        this.droolsRuleEngineStrategyImpl = droolsRuleEngineStrategyImpl;
    }

    @PostMapping("/result")
    public ResponseEntity<List<RuleResult>> getResult(@RequestBody User user) {
        List<RuleResult> results = droolsRuleEngineStrategyImpl.execute(user);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/rules/raw")
    public String getRawRules() throws IOException {
        ClassPathResource resource = new ClassPathResource("rules/user_detail.drl");
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }

    @GetMapping("/rules")
    public List<RuleDefinition> getRules() throws IOException {
        ClassPathResource resource = new ClassPathResource("rules/user_detail.drl");
        String drl = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        List<RuleDefinition> rules = new ArrayList<>();

        Pattern pattern = Pattern.compile(
                "RuleResult\\s*\\(\\s*\"(.*?)\"\\s*,\\s*\"(.*?)\"\\s*,\\s*.*?,\\s*\"(.*?)\"\\s*\\)",
                Pattern.DOTALL
        );

        Matcher matcher = pattern.matcher(drl);

        while (matcher.find()) {
            RuleDefinition def = new RuleDefinition();
            def.setComponent(matcher.group(1));
            def.setResult(matcher.group(2));
            def.setRule(matcher.group(3));
            rules.add(def);
        }

        return rules;
    }

}