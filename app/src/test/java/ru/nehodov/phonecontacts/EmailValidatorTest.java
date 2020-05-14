package ru.nehodov.phonecontacts;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmailValidatorTest {

    private Validator<String> validator;

    @Before
    public void before() {
        validator = new EmailValidator();
    }

    @Test
    public void validateWhenNullInput() {
        String email = null;

        assertFalse(validator.validate(email));
    }

    @Test
    public void validateWhenEmptyInput() {
        String email = "";

        assertFalse(validator.validate(email));
    }

    @Test
    public void validateWhenWithoutAtSymbol() {
        String email = "name_mail.com";

        assertFalse(validator.validate(email));
    }

    @Test
    public void validateWhenCorrectEmail() {
        String email = "name@mail.com";

        assertTrue(validator.validate(email));
    }

    @Test
    public void validateWhenWithoutDot() {
        String email = "name@mailcom";

        assertFalse(validator.validate(email));
    }

    @Test
    public void validateWhenTwoAts() {
        String email = "name@lastName@mail.com";

        assertFalse(validator.validate(email));
    }

    @Test
    public void validateWhenDomainHasNumber() {
        String email = "name@mail.c0m";

        assertFalse(validator.validate(email));
    }

    @Test
    public void validateWhenWithoutName() {
        String email = "@mail.com";

        assertFalse(validator.validate(email));
    }
}