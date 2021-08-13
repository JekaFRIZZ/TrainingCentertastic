package com.trainingcentertastic.validator;

import org.junit.Assert;
import org.junit.Test;

public class NameValidatorTest {
    private static final String INCORRECT_NAME = "<7asdfkasdbfajsdnflkasndfnasfjln1384";

    @Test
    public void testCheckNameShouldReturnTrueWhenCorrectNameApplied() {
        boolean actual = NameValidator.checkName("Eugene");
        Assert.assertTrue(actual);
    }

    @Test
    public void testCheckNameShouldReturnFalseWhenIncorrectNameApplied() {
        boolean actual = NameValidator.checkName(INCORRECT_NAME);
        Assert.assertFalse(actual);
    }


}