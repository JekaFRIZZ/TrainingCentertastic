package com.trainingcentertastic.validator;

import org.junit.Assert;
import org.junit.Test;


public class LinkValidatorTest {

    public static final String INCORRECT_LINK = "https://www.amazon.com/";
    public static final String CORRECT_LINK = "https://github.com/JekaFRIZZ/TrainingCentertastic";

    @Test
    public void testIsValidatorShouldReturnTrueWhenCorrectLinkApplied() {
        boolean actual = LinkValidator.isLink(CORRECT_LINK);
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidateShouldReturnFalseWhenIncorrectLinkApplied() {
        boolean actual = LinkValidator.isLink(INCORRECT_LINK);
        Assert.assertFalse(actual);
    }

}