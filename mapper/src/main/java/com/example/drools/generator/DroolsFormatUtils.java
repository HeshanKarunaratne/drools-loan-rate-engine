package com.example.drools.generator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heshan Karunaratne
 */
public class DroolsFormatUtils {
    public static String format(Object value) {
        return value instanceof String ? "\"" + value + "\"" : value.toString();
    }

    public static String formatList(Object value) {
        if (!(value instanceof List<?> list)) {
            throw new IllegalArgumentException("IN operator requires a list");
        }

        return list.stream()
                .map(DroolsFormatUtils::format)
                .collect(Collectors.joining(", "));
    }

    public static String negateBetween(String field, Object value) {

        if (!(value instanceof java.util.List<?> list) || list.size() != 2) {
            throw new IllegalArgumentException("Between operator requires exactly 2 values");
        }

        return field + " < " + format(list.get(0)) +
               " || " + field + " > " + format(list.get(1));
    }
}
