package com.example.group.petsogramapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


import com.example.group.petsogramapp.Backend.AccountManager;
import com.example.group.petsogramapp.Backend.DatabaseManager;
import com.example.group.petsogramapp.Backend.Updatable;
import com.example.group.petsogramapp.Backend.User;

import java.util.*;

public class SignUp extends AppCompatActivity implements Updatable {
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
    DatabaseManager databaseManager;
    User newUser;
    Intent intent_2;
    Bitmap profilePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        accountManager= AccountManager.getInstance();
        accountManager.setActivity(this);

        profilePhoto= BitmapFactory.decodeResource(getResources(), R.drawable.emptyprofile);

        databaseManager= DatabaseManager.getInstance();
        databaseManager.setActivity(this);

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
                nameEntrySignUp.setEnabled(false);
                phoneEntrySignUp.setEnabled(false);
                emailEntrySignUp.setEnabled(false);
                addressEntrySignUp.setEnabled(false);
                passwordEntrySignUp.setEnabled(false);
                confirmPasswordEntrySignUp.setEnabled(false);

                //info needed to be added in the database
                info.add(nameEntrySignUp.getText().toString());
                info.add(phoneEntrySignUp.getText().toString());
                info.add(addressEntrySignUp.getText().toString());
                emailRetrieved=emailEntrySignUp.getText().toString();
                passwordRetrieved_1=passwordEntrySignUp.getText().toString();
                passwordRetrieved_2=confirmPasswordEntrySignUp.getText().toString();
                accountManager.signUp(emailRetrieved,passwordRetrieved_1);

            }
        });
    }

    @Override
    public void updateUIFromDatabase()
    {
        intent_2.putExtra("userID",newUser.getID());
    }

    public void updateUIFromAuthentication()
    {
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
                    default:
                        newUser = new User(profilePhoto, info.get(0), emailRetrieved, info.get(1), info.get(2));
                        databaseManager.addDocument("Users", newUser);
                        intent_2 = new Intent(getApplicationContext(), Main2Activity.class);
                        finish();
                        startActivity(intent_2);

                }
            }

            //m3ana soura default

            newUser = new User(profilePhoto, info.get(0), emailRetrieved, info.get(1), info.get(2));
            databaseManager.addDocument("Users", newUser);
            intent_2 = new Intent(getApplicationContext(), Main2Activity.class);
            finish();
            startActivity(intent_2);
        }
        //emailEntrySignUp.getText().clear();
        passwordEntrySignUp.getText().clear();
        confirmPasswordEntrySignUp.getText().clear();
        passwordEntrySignUp.setEnabled(true);
        confirmPasswordEntrySignUp.setEnabled(true);

        errorTxt.setText("password not matching");

    }

    @Override
    public void updateUIFromStorage() {

    }
}