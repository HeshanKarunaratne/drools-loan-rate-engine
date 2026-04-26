package com.example.drools.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Heshan Karunaratne
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DynamicRuleRequest {
    private User user;
    private String drl;
}