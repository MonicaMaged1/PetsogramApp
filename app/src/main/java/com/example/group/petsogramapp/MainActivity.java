package com.example.group.petsogramapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent_GoToLogin = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent_GoToLogin);
    }
}
