package com.example.group.petsogramapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class AddNewPetActivity extends AppCompatActivity {
    private static final int GALLERY =1;
    private static final int CAMERA =2;
    Bitmap bitmap;

    private static final Object IMAGE_DIRECTORY ="/uploadedPics";
    DatePickerDialog picker;
    ImageView newPetProfilePictureImageView;
    Spinner typeSpinner;
    Spinner ageSpinner;
    EditText ageEditText;
    Button uploadPictreButton;
    Button addNewPetButton;
    EditText nameEditText;
    EditText speciesEditView;
    EditText birthDayEditText1;
    EditText bioEditText;
    EditText intersetsEditText;
    CheckBox maleCheckBox;
    CheckBox femaleCheckBox;
    boolean isMaleChecked;
    boolean isFemaleChecked;
    String Name;
    String Bio;
    String Interest;
    String birthDay;
    String Species;
    String Type;
    int day = 0;
    int month = 0;
    int year=0;
    boolean birthDayIsChosen=false;
    String date;



    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }
    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

//    public String saveImage(Bitmap myBitmap) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
//        File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
//         //have the object build the directory structure, if needed.
//        if (!wallpaperDirectory.exists()) {
//            wallpaperDirectory.mkdirs();
//        }
//
//        try {
//            File f = new File(wallpaperDirectory, Calendar.getInstance()
//                    .getTimeInMillis() + ".jpg");
//            f.createNewFile();
//            FileOutputStream fo = new FileOutputStream(f);
//            fo.write(bytes.toByteArray());
//            MediaScannerConnection.scanFile(this,
//                    new String[]{f.getPath()},
//                    new String[]{"image/jpeg"}, null);
//            fo.close();
//            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());
//
//            return f.getAbsolutePath();
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//        return "";
//    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_pet);
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        ageSpinner = (Spinner) findViewById(R.id.ageSpinner);
        ageEditText = findViewById(R.id.ageEditText);
        uploadPictreButton = findViewById(R.id.UploadPictreButton);
        addNewPetButton = findViewById(R.id.AddNewPetButton);
        newPetProfilePictureImageView = findViewById(R.id.NewPetProfilePictureImageView);
        nameEditText=findViewById(R.id.nameEditText);
        speciesEditView=findViewById(R.id.speciesEditView);
        bioEditText=findViewById(R.id.BioEditText);
        intersetsEditText=findViewById(R.id.interestEditText);
        maleCheckBox=findViewById(R.id.MaleCheckBox);
        femaleCheckBox=findViewById(R.id.FemaleCheckBox);
        isFemaleChecked=false;
        isMaleChecked=false;



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
        nameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                // When focus is lost check that the text field has valid values.

                if (!hasFocus) {
                    // Validate youredittext
                    Name=nameEditText.getText().toString();
                }
            }
            });
        speciesEditView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                // When focus is lost check that the text field has valid values.

                if (!hasFocus) {
                    // Validate youredittext
                    Species=speciesEditView.getText().toString();
                }
            }
        });


        ageEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                // When focus is lost check that the text field has valid values.

                if (!hasFocus) {
                    // Validate youredittext

                    birthDay=ageEditText.getText().toString();

                }
            }
        });


        ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                date=birthDay+ageSpinner.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        intersetsEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                // When focus is lost check that the text field has valid values.

                if (!hasFocus) {
                    // Validate youredittext
                    Interest=intersetsEditText.getText().toString();
                }
            }
        });


        bioEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                // When focus is lost check that the text field has valid values.

                if (!hasFocus) {
                    // Validate youredittext
                    Bio=bioEditText.getText().toString();
                }
            }
        });

        maleCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMaleChecked=true;
               femaleCheckBox.setChecked(false);
               isFemaleChecked=false;

            }
        });

        femaleCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFemaleChecked=true;
                maleCheckBox.setChecked(false);
                isMaleChecked=false;

            }
        });

        uploadPictreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();

            }
        });
        addNewPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(nameEditText.getText()))
                {
                    Toast.makeText(AddNewPetActivity.this, "Please enter the name of your pett", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(speciesEditView.getText()))
                {
                    Toast.makeText(AddNewPetActivity.this, "Please enter the species of your pet", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(typeSpinner.getSelectedItem().toString()))
                {
                    Toast.makeText(AddNewPetActivity.this, "Please enter the type of your pet", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(ageSpinner.getSelectedItem().toString()))
                {
                    Toast.makeText(AddNewPetActivity.this, "Please enter the age of your pet", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(ageSpinner.getSelectedItem().toString()))
                {
                    Toast.makeText(AddNewPetActivity.this, "Please enter the gender of your pet", Toast.LENGTH_SHORT).show();
                }
                else if(maleCheckBox.isChecked() && femaleCheckBox.isChecked() )
                {
                    Toast.makeText(AddNewPetActivity.this, "Please enter the gender of your pet", Toast.LENGTH_SHORT).show();
                }



            }
        });







    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    //String path = saveImage(bitmap);
                    newPetProfilePictureImageView.setImageBitmap(bitmap);
                    Toast.makeText(AddNewPetActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    //p.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(AddNewPetActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
             bitmap = (Bitmap) data.getExtras().get("data");
            newPetProfilePictureImageView.setImageBitmap(bitmap);
            //saveImage(thumbnail);
            Toast.makeText(AddNewPetActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }
}
