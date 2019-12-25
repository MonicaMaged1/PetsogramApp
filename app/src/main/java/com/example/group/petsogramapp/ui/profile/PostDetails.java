package com.example.group.petsogramapp.ui.profile;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group.petsogramapp.R;
import com.example.group.petsogramapp.ui.home.ViewCommentsActivity;
import com.example.group.petsogramapp.ui.home.ViewLikesActivity;

public class PostDetails extends AppCompatActivity {
    ImageView selectedImage;
    ImageView likeButton;
    TextView Likes;
    TextView Comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_post);

        selectedImage = (ImageView) findViewById(R.id.postImage);
        Intent Intent = getIntent();
        selectedImage.setImageResource(Intent.getIntExtra("image", 0));

        likeButton = (ImageView) findViewById(R.id.likeButton);
        Likes = (TextView) findViewById(R.id.Likes);
        Comments = (TextView) findViewById(R.id.Comments);
        likeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (likeButton.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.liked).getConstantState())
                    likeButton.setImageResource(R.drawable.ic_like);
                else
                    likeButton.setImageResource(R.drawable.liked);
            }
        });
        Likes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(getApplicationContext(), ViewLikesActivity.class);
                startActivity(Intent);
            }
        });
        Comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(getApplicationContext(), ViewCommentsActivity.class);
                startActivity(Intent);
            }
        });
    }
}
