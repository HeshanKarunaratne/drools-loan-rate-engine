package com.example.drools.generator;

/**
 * @author Heshan Karunaratne
 */
public class DroolsFormatUtils {
    public static String format(Object value) {
        return value instanceof String ? "\"" + value + "\"" : value.toString();
    }

}
