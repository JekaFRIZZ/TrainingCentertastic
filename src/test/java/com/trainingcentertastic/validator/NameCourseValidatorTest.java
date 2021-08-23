package com.trainingcentertastic.validator;

import org.junit.Assert;
import org.junit.Test;

public class NameCourseValidatorTest {

    @Test
    public void testCheckNameShouldReturnTrueWhenCorrectNameApplied() {
        boolean actual = NameCourseValidator.check("Патрик О'Брайан");
        Assert.assertTrue(actual);
    }
}