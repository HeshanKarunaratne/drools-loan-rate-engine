package com.example.drools.generator;

import com.example.drools.dto.RuleCondition;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heshan Karunaratne
 */
public class OperatorMapper {

    public static String map(String field, String operator, Object value) {

        return switch (operator) {
            case "=" -> field + " == " + format(value);
            case "!=" -> field + " != " + format(value);
            case ">" -> field + " > " + value;
            case ">=" -> field + " >= " + value;
            case "<" -> field + " < " + value;
            case "<=" -> field + " <= " + value;

            case "between" -> buildBetween(field, value);
            case "notBetween" -> "!(" + buildBetween(field, value) + ")";

            case "in" -> field + " in (" + formatList(value) + ")";
            case "notIn" -> field + " not in (" + formatList(value) + ")";

            default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
        };
    }

    private static String buildBetween(String field, Object value) {
        if (!(value instanceof List<?> list) || list.size() != 2) {
            throw new IllegalArgumentException("Between operator requires exactly 2 values");
        }

        return field + " >= " + format(list.get(0)) +
               " && " + field + " <= " + format(list.get(1));
    }

    private static String format(Object value) {
        if (value instanceof String) {
            return "\"" + value + "\"";
        }
        return value.toString();
    }

    private static String formatList(Object value) {
        if (!(value instanceof List<?> list)) {
            throw new IllegalArgumentException("IN operator requires a list");
        }

        return list.stream()
                .map(OperatorMapper::format)
                .collect(Collectors.joining(", "));
    }

    static String build(RuleCondition condition) {
        return OperatorMapper.map(
                condition.getField(),
                condition.getOperator(),
                condition.getValue()
        );
    }
}