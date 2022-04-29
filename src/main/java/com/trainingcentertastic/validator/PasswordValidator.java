package com.trainingcentertastic.validator;

public class PasswordValidator {
    private static String REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    public static boolean checkPassword(String name) {
            return name.matches(REGEX);
    }
}
