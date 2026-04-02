package com.example.drools;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/loan")
public class LoanController {
    private final DroolsService bankService;

    public LoanController(DroolsService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/rate")
    public ResponseEntity<Loan> getRate(@RequestBody User request) {
        Loan rate = bankService.getRate(request);
        return new ResponseEntity<>(rate, HttpStatus.OK);
    }

}