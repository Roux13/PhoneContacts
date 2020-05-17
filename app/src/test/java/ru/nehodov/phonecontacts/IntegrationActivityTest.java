package ru.nehodov.phonecontacts;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(RobolectricTestRunner.class)
@Config(minSdk = 21,
        maxSdk = 28)
public class IntegrationActivityTest {

    private static final String DEFAULT_MESSAGE = "...";
    private static final String VALIDATE_MESSAGE = "Success!";
    private static final String INVALIDATE_MESSAGE = "Validation error!!!";
    private static final String CORRECT_EMAIL = "name@mail.com";
    private static final String INCORRECT_EMAIL = "name@mail@.com";

    public ActivityScenario<EmailActivity> scenario;

    @Before
    public void init() {
        scenario = ActivityScenario.launch(EmailActivity.class);
    }

    @Test
    public void checkStatusError() {
        scenario.onActivity(activity -> {
            activity.findViewById(R.id.validateBtn).performClick();
            TextView result = activity.findViewById(R.id.result);
            String status = result.getText().toString();
            assertThat(status, is(INVALIDATE_MESSAGE));
        });

    }

    @Test
    public void setCorrectEmailAndCheckSuccessStatus() {
        scenario.onActivity(activity -> {
            EditText input = activity.findViewById(R.id.input);
            input.setText(CORRECT_EMAIL);
            Button validateBtn = activity.findViewById(R.id.validateBtn);
            validateBtn.performClick();
            TextView result = activity.findViewById(R.id.result);
            String status = result.getText().toString();
            assertThat(status, is(VALIDATE_MESSAGE));
        });
    }

}
