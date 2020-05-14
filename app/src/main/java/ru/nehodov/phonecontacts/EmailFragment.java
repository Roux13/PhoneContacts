package ru.nehodov.phonecontacts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EmailFragment extends Fragment {


    public EmailFragment() {
    }

    public static EmailFragment newInstance() {
        EmailFragment fragment = new EmailFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_email, container, false);
        EditText input = view.findViewById(R.id.input);
        Button validateBtn = view.findViewById(R.id.validateBtn);
        TextView result = view.findViewById(R.id.result);

        return view;
    }
}
