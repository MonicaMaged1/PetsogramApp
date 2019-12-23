package com.example.group.petsogramapp.add;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.group.petsogramapp.R;
import com.example.group.petsogramapp.ui.notifications.NotificationsViewModel;

public class UploadPhoto extends AppCompatActivity {

    private ImageView uploadedPhoto;
    Uri imageUri;
    private boolean isGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_photo_upload);

        Intent intent = getIntent();
        uploadedPhoto = (ImageView) findViewById(R.id.uploadedPhoto);
        isGallery = intent.getBooleanExtra("bool", isGallery);
        if(isGallery){
            imageUri = Uri.parse(getIntent().getStringExtra("imageUri"));
            uploadedPhoto.setImageURI(imageUri);
        }
        else{
            byte[] byteArray = getIntent().getByteArrayExtra("image");
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            uploadedPhoto.setImageBitmap(bmp);
        }

    }
}
