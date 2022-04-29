package com.trainingcentertastic.parser;

import java.util.Arrays;
import java.util.List;

public class RequirementParser {
    private final static String SPLITTER = "\\.";

    public List<String> parseRequirement(String requirement) {
        String[] template = requirement.split(SPLITTER);

        Arrays.parallelSetAll(template, i -> template[i].trim());

        List<String> requirements = Arrays.asList(template);
        return requirements;
    }
}
