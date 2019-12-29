package com.example.group.petsogramapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.*;
import java.awt.*;

public abstract class SignUp extends AppCompatActivity implements updateable {
    ImageView LogoView;
    TextView welcomeText, nameSignUp, emailSignUp, mobileSignUp, addressSignUp, passwordSignUp, confirmPasswordSignUp,errorTxt;
    EditText nameEntrySignUp, emailEntrySignUp, phoneEntrySignUp,addressEntrySignUp, passwordEntrySignUp, confirmPasswordEntrySignUp;
    Button createAccountButton;
    ArrayList<String> info= new ArrayList<>();
    boolean updatingCreateAccount=false;


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
        addressEntrySignUp = (EditText) findViewById(R.id.addressEntrySignUp);
        passwordEntrySignUp = (EditText) findViewById(R.id.passwordEntrySignUp);
        confirmPasswordEntrySignUp = (EditText) findViewById(R.id.confirmPasswordEntrySignUp);
        createAccountButton=(Button) findViewById(R.id.createAccountButton);
        errorTxt=(TextView) findViewById(R.id.errorTxt);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameEntrySignUp.getText().toString().isEmpty() || emailEntrySignUp.getText().toString().isEmpty() || phoneEntrySignUp.getText().toString().isEmpty() ||addressEntrySignUp.getText().toString().isEmpty() ||confirmPasswordEntrySignUp.getText().toString().isEmpty() ||passwordEntrySignUp.getText().toString().isEmpty())
                {
                    errorTxt.setText("you must fill in all the fields");
                }

                updatingCreateAccount=true;
            }
        });
    }
    public void updateUI()
    {


}

