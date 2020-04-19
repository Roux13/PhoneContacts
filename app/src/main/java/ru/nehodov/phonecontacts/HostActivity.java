package ru.nehodov.phonecontacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class HostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.host_layout);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.host_layout);

        if (fragment == null) {
            fm.beginTransaction()
                    .add(R.id.host_layout, new ContactsFragment())
                    .commit();
        }
    }
}
