package com.example.group.petsogramapp.ui.profile;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.group.petsogramapp.R;

public class PostDetails extends AppCompatActivity {
    ImageView selectedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        selectedImage = (ImageView) findViewById(R.id.selectedImage);
        Intent Intent = getIntent();
        selectedImage.setImageResource(Intent.getIntExtra("image", 0));
    }
}
