package com.example.group.petsogramapp.ui.profile;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.group.petsogramapp.R;

import java.util.ArrayList;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;

public class CustomAdapter extends BaseAdapter {
    Context context;
    String petNames[];
    int petProfilePictures[];
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, String[] petNames, int[] petProfilePictures) {
        this.context = context;
        this.petNames = petNames;
        this.petProfilePictures = petProfilePictures;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return petNames.length;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.my_pets_rows, null);
        TextView petNameTextView = (TextView) view.findViewById(R.id.petNameTextView);
        ImageView petProfilePicture = (ImageView) view.findViewById(R.id.petProfilePicture);
        petNameTextView.setText(petNames[i]);
        petProfilePicture.setImageResource(petProfilePictures[i]);
        return view;
    }
}