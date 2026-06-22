package com.example.drools.generator;

import com.example.drools.dto.RuleCondition;

import static com.example.drools.generator.DroolsFormatUtils.*;

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

    public static String negate(RuleCondition condition) {

        String field = condition.getField();
        String operator = condition.getOperator();
        Object value = condition.getValue();

        return switch (operator) {

            // ===== NUMERIC =====
            case "=" -> field + " != " + format(value);
            case "!=" -> field + " == " + format(value);
            case ">" -> field + " <= " + value;
            case ">=" -> field + " < " + value;
            case "<" -> field + " >= " + value;
            case "<=" -> field + " > " + value;

            case "between" -> negateBetween(field, value);
            case "notBetween" -> OperatorMapper.map(field, "between", value);

            // ===== SELECT =====
            case "in" -> field + " not in (" + formatList(value) + ")";
            case "notIn" -> field + " in (" + formatList(value) + ")";

            default -> throw new IllegalArgumentException("Unsupported operator for negation: " + operator);
        };
    }

}