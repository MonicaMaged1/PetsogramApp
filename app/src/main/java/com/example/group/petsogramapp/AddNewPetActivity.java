package com.example.group.petsogramapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddNewPetActivity extends AppCompatActivity {
    DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_pet);
        final Spinner typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        final EditText birthDayEditText = findViewById(R.id.birthDayEditText1);
        String selectedAnimalType = typeSpinner.getSelectedItem().toString();
        List<String> categories = new ArrayList<String>();
        if(selectedAnimalType=="Cats") {
            categories.add("Shirazi");
            categories.add("Seyami");
            categories.add("Abyssinian");
            categories.add("Aegean");
            categories.add("American Curl");
            categories.add("Bambino");
        }
        else if(selectedAnimalType=="Dog") {
            categories.add("Golden Retriever");
            categories.add("German Shepherd");
            categories.add("Husky");
            categories.add("Beagle");
            categories.add("Beauceron");
            categories.add("Billy");
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.activity_add_new_pet, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.activity_add_new_pet);
        // attaching data adapter to spinner

        birthDayEditText.setInputType(InputType.TYPE_NULL);
        birthDayEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddNewPetActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                birthDayEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });


    }
}
