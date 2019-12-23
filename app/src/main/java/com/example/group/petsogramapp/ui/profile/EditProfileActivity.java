package com.example.group.petsogramapp.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.group.petsogramapp.R;

import java.io.IOException;

public class EditProfileActivity extends AppCompatActivity {
    private ImageView profilePicture;
    private Button editPictureButton;
    private CheckBox maleCheckBox;
    private CheckBox femaleCheckBox;
    boolean isMaleChecked =false;
    boolean isFemaleChecked=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        profilePicture = (ImageView) findViewById(R.id.profilePicture);
        editPictureButton =(Button) findViewById(R.id.editPictureButton);
        maleCheckBox=(CheckBox ) findViewById(R.id.maleCheckBox);
        femaleCheckBox=(CheckBox ) findViewById(R.id.femaleCheckBox);
        editPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage(EditProfileActivity.this);

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


    }



    private void selectImage(Context context) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your image");
        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {

//                    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_view);
//                    navigation.setSelectedItemId(R.id.navigation_home);
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                        if (bitmap != null) {
                            profilePicture.setImageBitmap(bitmap);
                        }
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        if (data != null) {
                            Uri contentURI = data.getData();
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), contentURI);
                                profilePicture.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();

                            }
                        }
                    }
                    break;

            }

        }
    }
}
