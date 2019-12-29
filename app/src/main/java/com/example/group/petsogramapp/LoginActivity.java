package com.example.group.petsogramapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.Button;
import android.widget.*;
import android.widget.Toast;

import com.example.group.petsogramapp.Backend.AccountManager;
import com.example.group.petsogramapp.Backend.Updatable;

import java.math.RoundingMode;


public class LoginActivity extends AppCompatActivity implements Updatable {
    ImageView LogoView;
    TextView welcomeText, emailLabel, passwordLabel,possibleErrorText;
    EditText emailEntry, passwordEntry;
    Button signupButton, loginButton;
    boolean updatingLogIn=false;
    boolean updatingSignUp=false;
    String emailRetrieved;
    String passwordRetrieved;
    int currentErrorStatus;
    int taskStatus;
    AccountManager accountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountManager= AccountManager.getInstance();
        accountManager.setActivity(this);
        LogoView=(ImageView) findViewById(R.id.LogoView);
        welcomeText=(TextView) findViewById(R.id.welcomeText);
        emailLabel=(TextView) findViewById(R.id.emailLabel);
        passwordLabel=(TextView) findViewById(R.id.passwordLabel);
        emailEntry=(EditText) findViewById(R.id.emailEntry);
        passwordEntry=(EditText) findViewById(R.id.passwordEntry);
        signupButton=(Button) findViewById(R.id.signupButton);
        loginButton=(Button) findViewById(R.id.loginButton);
        possibleErrorText=(TextView)findViewById(R.id.possibleErrorText);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatingLogIn=true;
                emailEntry.setFocusable(false);
                passwordEntry.setFocusable(false);
                emailRetrieved= emailEntry.getText().toString();
                passwordRetrieved= passwordEntry.getText().toString();
                accountManager.signIn(emailRetrieved,passwordRetrieved);

            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatingSignUp=true;
                Intent intent_SignUp = new Intent(getApplicationContext(), SignUp.class);
                    startActivity(intent_SignUp);

            }
        });
    }


    @Override
    public void updateUI() {
        possibleErrorText.setText("please wait...logging you in");
        if(updatingLogIn)
        {

            currentErrorStatus=accountManager.getErrorStatus();
            taskStatus=accountManager.getTaskStatus();

            if(taskStatus==AccountManager.ERROR) {
                switch (currentErrorStatus) {
                    case(AccountManager.NETWORK_ERROR):
                        possibleErrorText.setText("please check your internet connection");
                        emailEntry.getText().clear();
                        passwordEntry.getText().clear();
                    case (AccountManager.INVALID_EMAIL):
                        emailEntry.getText().clear();
                        emailEntry.setFocusable(true);
                        //passwordEntry.setFocusable(true);
                        possibleErrorText.setText("Invalid Email Format , please re-enter your email");
                        break;
                    case (AccountManager.WRONG_PASSWORD):
                        passwordEntry.getText().clear();
                        //emailEntry.setFocusable(true);
                        passwordEntry.setFocusable(true);
                        possibleErrorText.setText("wrong password , please re-enter your password");
                        break;
                    case (AccountManager.USER_DISABLED):
                        emailEntry.getText().clear();
                        passwordEntry.getText().clear();
                        possibleErrorText.setText("This user has been disabled , try logging in with another user or create a new account");
                        emailEntry.setFocusable(true);
                        passwordEntry.setFocusable(true);
                        break;
                    case (AccountManager.USER_NOT_FOUND):
                        emailEntry.getText().clear();
                        passwordEntry.getText().clear();
                        possibleErrorText.setText("User not found , try logging in with another user or create a new account");
                        emailEntry.setFocusable(true);
                        passwordEntry.setFocusable(true);
                        break;
                    default:
                        Intent intent_1 = new Intent(getApplicationContext(), Main2Activity.class);
                        startActivity(intent_1);

                }

            }
            Intent intent_1 = new Intent(getApplicationContext(), Main2Activity.class);
            startActivity(intent_1);
        }
        }
    }

