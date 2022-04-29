package com.trainingcentertastic.validator;

public class MarkValidator {

    public static boolean checkMark(String stringMark){
        try {
            int mark = Integer.parseInt(stringMark);
            return (mark >= 0 && mark <= 10);
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}
