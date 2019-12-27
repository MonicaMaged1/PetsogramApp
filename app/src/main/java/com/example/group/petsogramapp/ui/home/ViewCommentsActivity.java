package com.example.group.petsogramapp.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.group.petsogramapp.R;

public class ViewCommentsActivity extends AppCompatActivity {
    ListView viewCommentsListView;


    //video 9 and 10

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_comments);
        viewCommentsListView = (ListView) findViewById(R.id.viewCommentsListView);
    }
}
