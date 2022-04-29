package com.trainingcentertastic.validator;

import org.junit.Assert;
import org.junit.Test;

public class MarkValidatorTest {

    private static final String INCORRECT_STRING = "7asdfkasdbfajsdnflkasndfnasfjln1384";

    @Test
    public void testCheckMarkShouldReturnTrueWhenCorrectMarkApplied() {
        boolean actual = MarkValidator.checkMark("7");
        Assert.assertTrue(actual);
    }

    @Test
    public void testCheckMarkShouldReturnFalseWhenIncorrectTextApplied() {
        boolean actual = MarkValidator.checkMark(INCORRECT_STRING);
        Assert.assertFalse(actual);
    }

}