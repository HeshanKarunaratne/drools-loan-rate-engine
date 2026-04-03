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
public class RuleDefinition {
    private String component;
    private String result;
    private String rule;
}