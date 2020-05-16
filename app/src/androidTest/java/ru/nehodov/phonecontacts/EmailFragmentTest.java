package ru.nehodov.phonecontacts;

import android.content.Intent;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@LargeTest
public class EmailFragmentTest {

    private static final String DEFAULT_MESSAGE = "...";
    private static final String VALIDATE_MESSAGE = "Success!";
    private static final String INVALIDATE_MESSAGE = "Validation error!!!";
    private static final String CORRECT_EMAIL = "name@mail.com";
    private static final String INCORRECT_EMAIL = "name@mail@.com";

    @Rule
    public ActivityTestRule<HostActivity> activityTestRule = new ActivityTestRule<>(HostActivity.class);

    @Before
    public void initHostActivity() {
        activityTestRule.launchActivity(new Intent());
        FragmentScenario scenario = FragmentScenario.launchInContainer(
                EmailFragment.class,
                null,
                R.layout.host_layout,
                null);
    }

    @Test
    public void checkDefaultResultMessage() {
        onView(withId(R.id.result)).check(matches(withText(DEFAULT_MESSAGE)));
    }

    @Test
    public void checkButtonIsClickable() {
        onView(withId(R.id.validateBtn)).check(matches(isClickable()));
    }

    @Test
    public void checkMessageWhenIncorrectEmail() {
        onView(withId(R.id.input)).perform(typeText(INCORRECT_EMAIL));
        onView(withId(R.id.validateBtn)).perform(click());
        onView(withId(R.id.result)).check(matches(withText(INVALIDATE_MESSAGE)));
    }

    @Test
    public void checkMessageWhenCorrectEmail() {
        onView(withId(R.id.input)).perform(typeText(CORRECT_EMAIL));
        onView(withId(R.id.validateBtn)).perform(click());
        onView(withId(R.id.result)).check(matches(withText(VALIDATE_MESSAGE)));
    }
}