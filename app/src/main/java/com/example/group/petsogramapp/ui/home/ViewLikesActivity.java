package com.example.group.petsogramapp.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.group.petsogramapp.R;

public class ViewLikesActivity extends AppCompatActivity {
    ListView viewLikesListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_likes);
        viewLikesListView = (ListView) findViewById(R.id.viewLikesListView);
    }
}
