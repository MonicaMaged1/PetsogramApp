package com.example.group.petsogramapp.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group.petsogramapp.R;
//import com.example.group.petsogramapp.ui.profile.OtherUserProfile;

public class HomeImageAdapter extends BaseAdapter {

    Context Context;
    int homePosts[];
    LayoutInflater Inflater;
    ImageView likeButton;
    TextView Likes;
    TextView  Comments;
    TextView petName;

    public HomeImageAdapter(Context applicationContext, int[] homePosts) {
        this.Context = applicationContext;
        this.homePosts = homePosts;
        Inflater = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return homePosts.length;
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View myHomeView, ViewGroup viewGroup) {
        myHomeView = Inflater.inflate(R.layout.layout_post, null);
        ImageView postImage = (ImageView) myHomeView.findViewById(R.id.postImage);
        postImage.setImageResource(homePosts[i]);

        likeButton = (ImageView) myHomeView.findViewById(R.id.likeButton);
        Likes = (TextView) myHomeView.findViewById(R.id.Likes);
        Comments = (TextView) myHomeView.findViewById(R.id.Comments);
        petName =(TextView) myHomeView.findViewById(R.id.petName);

        petName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent Intent = new Intent(Context, OtherUserProfile.class);
//                Context.startActivity(Intent);
            }
        });

        likeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (likeButton.getDrawable().getConstantState() == Context.getResources().getDrawable(R.drawable.liked).getConstantState())
                    likeButton.setImageResource(R.drawable.ic_like);
                else
                    likeButton.setImageResource(R.drawable.liked);
            }
        });
        Likes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(Context, ViewLikesActivity.class);
                Context.startActivity(Intent);
            }
        });
        Comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(Context, ViewCommentsActivity.class);
                Context.startActivity(Intent);
            }
        });

        return myHomeView;
    }
}
