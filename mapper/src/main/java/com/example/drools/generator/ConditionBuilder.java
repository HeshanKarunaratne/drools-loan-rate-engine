package com.example.drools.generator;

import com.example.drools.dto.RuleCondition;

/**
 * @author Heshan Karunaratne
 */
public class ConditionBuilder {

    public static String build(RuleCondition condition) {

        return OperatorMapper.map(
                condition.getField(),
                condition.getOperator(),
                condition.getValue()
        );
    }
}