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
public class RuleCondition {
    private String id;
    private String field;
    private String operator;
    private String valueSource;
    private Object value;
}