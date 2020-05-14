package ru.nehodov.phonecontacts;

import java.util.regex.Pattern;

public class EmailValidator implements Validator<String> {

    private static final String EMAIL_REGEX = "^(\\w)+@(\\w)+\\.([A-Za-z])+$";

    @Override
    public boolean validate(String email) {
        if (email == null) {
            return false;
        }
        return Pattern.matches(EMAIL_REGEX, email);
    }
}
