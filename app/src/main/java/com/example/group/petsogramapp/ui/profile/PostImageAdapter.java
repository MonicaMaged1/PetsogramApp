package com.example.group.petsogramapp.ui.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.group.petsogramapp.R;

public class PostImageAdapter extends BaseAdapter {
    Context Context;
    int myPosts[];
    LayoutInflater Inflater;
    public PostImageAdapter(Context applicationContext, int[] myPosts) {
        this.Context = applicationContext;
        this.myPosts = myPosts;
        Inflater = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return myPosts.length;
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
    public View getView(int i, View profileImagesView, ViewGroup viewGroup) {
        profileImagesView = Inflater.inflate(R.layout.layout_gridview_profile, null);
        ImageView myPost = (ImageView) profileImagesView.findViewById(R.id.myPostId);
        myPost.setImageResource(myPosts[i]);
        return profileImagesView;
    }
}
