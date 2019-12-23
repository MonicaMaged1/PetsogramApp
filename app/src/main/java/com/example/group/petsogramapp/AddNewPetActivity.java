package com.example.group.petsogramapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class AddNewPetActivity extends AppCompatActivity {
    private static final int GALLERY =1;
    private static final int CAMERA =2;
    Bitmap bitmap;

    private static final Object IMAGE_DIRECTORY ="/uploadedPics";
    DatePickerDialog picker;
    ImageView newPetProfilePictureImageView;
    Spinner typeSpinner;
    EditText birthDayEditText;
    Button uploadPictreButton;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_pet);
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        birthDayEditText = findViewById(R.id.birthDayEditText1);
        uploadPictreButton = findViewById(R.id.UploadPictreButton);
        newPetProfilePictureImageView = findViewById(R.id.NewPetProfilePictureImageView);
        nameEditText=findViewById(R.id.nameEditText);
        speciesEditView=findViewById(R.id.speciesEditView);
        birthDayEditText1=findViewById(R.id.birthDayEditText1);
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


        birthDayEditText.setInputType(InputType.TYPE_NULL);
        birthDayEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddNewPetActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                birthDayEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);

                picker.show();
                birthDay=birthDayEditText.getText().toString();
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
