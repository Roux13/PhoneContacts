package ru.nehodov.phonecontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_email);
        EditText input = findViewById(R.id.input);
        Button validateBtn = findViewById(R.id.validateBtn);
        TextView result = findViewById(R.id.result);

        Validator<String> validator = new EmailValidator();
        validateBtn.setOnClickListener(v -> {
            String email = input.getText().toString();
            if (validator.validate(email)) {
                result.setText("Success!");
            } else {
                result.setText("Validation error!!!");
            }
        });


    }
}
