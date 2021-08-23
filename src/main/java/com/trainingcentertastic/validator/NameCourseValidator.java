package com.trainingcentertastic.validator;

public class NameCourseValidator {
    private static String REGEX = "^[\\p{L} .'-]+$";

    public static boolean check(String text) {
        return text.matches(REGEX);
    }
}
