package com.trainingcentertastic.validator;

public class NameValidator {
    private static String REGEX = "^[A-Za-z]\\w{3,29}$";

    public static boolean checkName(String name) {
        return name.matches(REGEX);
    }
}
