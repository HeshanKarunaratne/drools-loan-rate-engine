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
    private final DroolsService droolsService;

    public LoanController(DroolsService droolsService) {
        this.droolsService = droolsService;
    }

    @PostMapping("/result")
    public ResponseEntity<List<RuleResult>> getResult(@RequestBody User request) {
        List<RuleResult> results = droolsService.getResults(request);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

}