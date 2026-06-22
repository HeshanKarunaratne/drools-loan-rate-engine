package com.example.drools.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Heshan Karunaratne
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RuleRequest {
    private Group group;
    private List<RuleCondition> rules;
}
