package com.example.group.petsogramapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;

public class SignUp extends AppCompatActivity implements updateable {
    ImageView LogoView;
    TextView welcomeText, nameSignUp, emailSignUp, mobileSignUp, addressSignUp, passwordSignUp, confirmPasswordSignUp;
    EditText nameEntrySignUp, emailEntrySignUp, phoneEntrySignUp, passwordEntrySignUp, confirmPasswordEntrySignUp;
    Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        LogoView = (ImageView) findViewById(R.id.LogoView);
        welcomeText = (TextView) findViewById(R.id.welcomeText);
        nameSignUp = (TextView) findViewById(R.id.nameSignUp);
        emailSignUp = (TextView) findViewById(R.id.emailSignUp);
        mobileSignUp = (TextView) findViewById(R.id.mobileSignUp);
        addressSignUp = (TextView) findViewById(R.id.addressSignUp);
        passwordSignUp = (TextView) findViewById(R.id.passwordSignUp);
        confirmPasswordSignUp = (TextView) findViewById(R.id.confirmPasswordSignUp);
        nameEntrySignUp = (EditText) findViewById(R.id.nameEntrySignUp);
        emailEntrySignUp = (EditText) findViewById(R.id.emailEntrySignUp);
        phoneEntrySignUp = (EditText) findViewById(R.id.phoneEntrySignUp);
        nameEntrySignUp = (EditText) findViewById(R.id.nameEntrySignUp);
        nameEntrySignUp = (EditText) findViewById(R.id.nameEntrySignUp);
        nameEntrySignUp = (EditText) findViewById(R.id.nameEntrySignUp);
    }
}
