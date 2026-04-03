package com.example.drools;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/loan")
public class LoanController {
    private final DroolsService bankService;

    public LoanController(DroolsService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/rate")
    public ResponseEntity<List<RuleResult>> getRate(@RequestBody User request) {
        List<RuleResult> results = bankService.getResults(request);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

}