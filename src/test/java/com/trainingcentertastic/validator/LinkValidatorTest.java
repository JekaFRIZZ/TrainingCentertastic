package com.trainingcentertastic.validator;

import org.junit.Assert;
import org.junit.Test;


public class LinkValidatorTest {

    public static final String INCORRECT_LINK = "https://www.amazon.com/";
    public static final String CORRECT_LINK = "https://github.com/JekaFRIZZ/TrainingCentertastic";

    @Test
    public void testIsValidatorShouldReturnTrueWhenCorrectLinkApplied() {
        LinkValidator linkValidator = new LinkValidator();
        boolean actual = linkValidator.isLink(CORRECT_LINK);
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidateShouldReturnFalseWhenIncorrectLinkApplied() {
        LinkValidator linkValidator = new LinkValidator();
        boolean actual = linkValidator.isLink(INCORRECT_LINK);
        Assert.assertFalse(actual);
    }

}