package com.example.group.petsogramapp.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.group.petsogramapp.R;

import java.io.IOException;
import java.util.NoSuchElementException;

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
//    private void scaleImage(ImageView view) throws NoSuchElementException {
//        // Get bitmap from the the ImageView.
//        Bitmap bitmap = null;
//
//        try {
//            Drawable drawing = view.getDrawable();
//            bitmap = ((BitmapDrawable) drawing).getBitmap();
//        } catch (NullPointerException e) {
//            throw new NoSuchElementException("No drawable on given view");
//        } catch (ClassCastException e) {
//            // Check bitmap is Ion drawable
//            bitmap = Ion.with(view).getBitmap();
//        }
//
//        // Get current dimensions AND the desired bounding box
//        int width = 0;
//
//        try {
//            width = bitmap.getWidth();
//        } catch (NullPointerException e) {
//            throw new NoSuchElementException("Can't find bitmap on given view/drawable");
//        }
//
//        int height = bitmap.getHeight();
//        int bounding = dpToPx(250);
//        Log.i("Test", "original width = " + Integer.toString(width));
//        Log.i("Test", "original height = " + Integer.toString(height));
//        Log.i("Test", "bounding = " + Integer.toString(bounding));
//
//        // Determine how much to scale: the dimension requiring less scaling is
//        // closer to the its side. This way the image always stays inside your
//        // bounding box AND either x/y axis touches it.
//        float xScale = ((float) bounding) / width;
//        float yScale = ((float) bounding) / height;
//        float scale = (xScale <= yScale) ? xScale : yScale;
//        Log.i("Test", "xScale = " + Float.toString(xScale));
//        Log.i("Test", "yScale = " + Float.toString(yScale));
//        Log.i("Test", "scale = " + Float.toString(scale));
//
//        // Create a matrix for the scaling and add the scaling data
//        Matrix matrix = new Matrix();
//        matrix.postScale(scale, scale);
//
//        // Create a new bitmap and convert it to a format understood by the ImageView
//        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
//        width = scaledBitmap.getWidth(); // re-use
//        height = scaledBitmap.getHeight(); // re-use
//        BitmapDrawable result = new BitmapDrawable(scaledBitmap);
//        Log.i("Test", "scaled width = " + Integer.toString(width));
//        Log.i("Test", "scaled height = " + Integer.toString(height));
//
//        // Apply the scaled bitmap
//        view.setImageDrawable(result);
//
//        // Now change ImageView's dimensions to match the scaled image
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
//        params.width = width;
//        params.height = height;
//        view.setLayoutParams(params);
//
//        Log.i("Test", "done");
//    }
//
//    private int dpToPx(int dp) {
//        float density = getApplicationContext().getResources().getDisplayMetrics().density;
//        return Math.round((float)dp * density);
//    }
}
