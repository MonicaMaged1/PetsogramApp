package com.example.group.petsogramapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


import com.example.group.petsogramapp.Backend.AccountManager;
import com.example.group.petsogramapp.Backend.Updatable;

import java.util.*;
import java.awt.*;

public abstract class SignUp extends AppCompatActivity implements Updatable {
    ImageView LogoView;
    TextView welcomeText, nameSignUp, emailSignUp, mobileSignUp, addressSignUp, passwordSignUp, confirmPasswordSignUp,errorTxt;
    EditText nameEntrySignUp, emailEntrySignUp, phoneEntrySignUp,addressEntrySignUp, passwordEntrySignUp, confirmPasswordEntrySignUp;
    Button createAccountButton;
    ArrayList<String> info= new ArrayList<>();
    String emailRetrieved;
    String passwordRetrieved_1;
    String passwordRetrieved_2;
    int currentTaskStatus;
    int currentErrorStatus;
    boolean updatingCreateAccount=false;
    AccountManager accountManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        accountManager= AccountManager.getInstance();
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
                nameEntrySignUp.setFocusable(false);
                phoneEntrySignUp.setFocusable(false);
                emailEntrySignUp.setFocusable(false);
                addressEntrySignUp.setFocusable(false);
                passwordEntrySignUp.setFocusable(false);
                confirmPasswordEntrySignUp.setFocusable(false);

                //info needed to be added in the database
                info.add(nameEntrySignUp.getText().toString());
                info.add(phoneEntrySignUp.getText().toString());
                info.add(addressEntrySignUp.getText().toString());

                accountManager.setActivity(SignUp.this);
            }
        });
    }
    public void updateUI()
    {
        emailRetrieved=emailEntrySignUp.getText().toString();
        passwordRetrieved_1=passwordEntrySignUp.getText().toString();
        passwordRetrieved_2=confirmPasswordEntrySignUp.getText().toString();
        if(passwordRetrieved_1.equals(passwordRetrieved_2))
        {
            accountManager.signUp(emailRetrieved,passwordRetrieved_1);
            currentErrorStatus=accountManager.getErrorStatus();
            currentTaskStatus=accountManager.getTaskStatus();
            if(currentTaskStatus==accountManager.ERROR)
            {
                switch(currentErrorStatus)
                {
                    case(AccountManager.NETWORK_ERROR):
                        errorTxt.setText("please check your internet connection");
                        emailEntrySignUp.getText().clear();
                        passwordEntrySignUp.getText().clear();
                        confirmPasswordEntrySignUp.getText().clear();
                        break;
                    case(AccountManager.INVALID_EMAIL):
                        errorTxt.setText("please enter the email in the right format");
                        emailEntrySignUp.getText().clear();
                        passwordEntrySignUp.getText().clear();
                        confirmPasswordEntrySignUp.getText().clear();
                        emailEntrySignUp.setFocusable(true);
                        passwordEntrySignUp.setFocusable(true);
                        confirmPasswordEntrySignUp.setFocusable(true);
                        break;
                    case(AccountManager.EMAIL_ALREADY_IN_USE):
                        errorTxt.setText("This email is already in use");
                        emailEntrySignUp.getText().clear();
                        passwordEntrySignUp.getText().clear();
                        confirmPasswordEntrySignUp.getText().clear();
                        emailEntrySignUp.setFocusable(true);
                        passwordEntrySignUp.setFocusable(true);
                        confirmPasswordEntrySignUp.setFocusable(true);
                        break;
                    case(AccountManager.WEAK_PASSWORD):
                        errorTxt.setText("This is a weak password , please try another one");
                        passwordEntrySignUp.getText().clear();
                        confirmPasswordEntrySignUp.getText().clear();
                        passwordEntrySignUp.setFocusable(true);
                        confirmPasswordEntrySignUp.setFocusable(true);
                        break;
                }
            }
            Intent intent_2 = new Intent(getApplicationContext(), Main2Activity.class);
            startActivity(intent_2);
        }
        //emailEntrySignUp.getText().clear();
        passwordEntrySignUp.getText().clear();
        confirmPasswordEntrySignUp.getText().clear();
        errorTxt.setText("password not matching");
    }
}