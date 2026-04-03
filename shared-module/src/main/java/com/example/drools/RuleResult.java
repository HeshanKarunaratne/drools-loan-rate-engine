package com.example.drools;

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
public class RuleResult {
    private String component;
    private String result;
    private Object value;
    private String rule;
}