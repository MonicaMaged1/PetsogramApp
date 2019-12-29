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

import java.math.RoundingMode;


public class LoginActivity extends AppCompatActivity {
    ImageView LogoView;
    TextView welcomeText, emailLabel, passwordLabel;
    EditText emailEntry, passwordEntry;
    Button signupButton, loginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LogoView=(ImageView) findViewById(R.id.LogoView);
        welcomeText=(TextView) findViewById(R.id.welcomeText);
        emailLabel=(TextView) findViewById(R.id.emailLabel);
        passwordLabel=(TextView) findViewById(R.id.passwordLabel);
        emailEntry=(EditText) findViewById(R.id.emailEntry);
        passwordEntry=(EditText) findViewById(R.id.passwordEntry);
        signupButton=(Button) findViewById(R.id.signupButton);
        loginButton=(Button) findViewById(R.id.loginButton);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginVerified())
                {
                    Intent intent_1 = new Intent(getApplicationContext(), Main2Activity.class);
                    startActivity(intent_1);
                }
                else
                {
                    emailEntry.getText().clear();
                    passwordEntry.getText().clear();
                }
                }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_2 = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent_2);
            }
        });
    }
private boolean loginVerified()
{
    return false;
}
}
