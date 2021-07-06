package com.trainingcentertastic.validator;

public class LinkValidator {

    private String REGEX = "\\b(https?|http)://github.com/[-a-zA-Z0-9+&#/%?=~_|!:,.;]*[-a-zA-Z0-9+&#/%=~_|]";

    public boolean isLink(String text) {
        return text.matches(REGEX);
    }
}
