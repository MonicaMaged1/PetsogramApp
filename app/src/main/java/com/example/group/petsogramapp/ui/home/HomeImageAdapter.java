package com.example.group.petsogramapp.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.group.petsogramapp.R;

public class HomeImageAdapter extends BaseAdapter {

    Context Context;
    int homePosts[];
    LayoutInflater Inflater;
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
        return myHomeView;
    }
}
