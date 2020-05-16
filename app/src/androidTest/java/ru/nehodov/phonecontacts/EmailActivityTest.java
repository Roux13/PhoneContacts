package ru.nehodov.phonecontacts;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
public class EmailActivityTest {

    private static final String DEFAULT_MESSAGE = "...";
    private static final String VALIDATE_MESSAGE = "Success!";
    private static final String INVALIDATE_MESSAGE = "Validation error!!!";
    private static final String CORRECT_EMAIL = "name@mail.com";
    private static final String INCORRECT_EMAIL = "name@mail@.com";

    @Rule
    public ActivityScenarioRule<EmailActivity> activityTestRule
            = new ActivityScenarioRule<>(EmailActivity.class);

    @Test
    public void checkLabelText() {
        onView(withId(R.id.result)).check(matches(withText(DEFAULT_MESSAGE)));
    }

    @Test
    public void checkButtonIsClickable() {
        onView(withId(R.id.validateBtn)).check(matches(isClickable()));
    }

    @Test
    public void checkLabelWithError() {
        onView(withId(R.id.validateBtn)).perform(click());
        onView(withId(R.id.result)).check(matches(withText(INVALIDATE_MESSAGE)));
    }

    @Test
    public void checkLabelWhenInputCorrectEmail() {
        onView(withId(R.id.input)).perform(typeText(CORRECT_EMAIL));
        onView(withId(R.id.validateBtn)).perform(click());
        onView(withId(R.id.result)).check(matches(withText(VALIDATE_MESSAGE)));
    }

    @Test
    public void checkLabelWhenInputIncorrectEmail() {
        onView(withId(R.id.input)).perform(typeText(INCORRECT_EMAIL));
        onView(withId(R.id.validateBtn)).perform(click());
        onView(withId(R.id.result)).check(matches(withText(INVALIDATE_MESSAGE)));
    }
}