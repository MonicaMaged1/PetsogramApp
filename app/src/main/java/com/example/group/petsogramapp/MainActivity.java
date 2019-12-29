package com.example.group.petsogramapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.group.petsogramapp.Backend.DatabaseManager;
import com.example.group.petsogramapp.Backend.Pet;
import com.example.group.petsogramapp.Backend.StorageManager;
import com.example.group.petsogramapp.Backend.Updatable;
import com.example.group.petsogramapp.Backend.User;


public class MainActivity extends AppCompatActivity implements Updatable
{
    User u;
    Pet p;
    ImageView imageView;
    DatabaseManager databaseManager;
    StorageManager storageManager;
    boolean flag;
    boolean flag2;
    Bitmap photo;
    String id1 ="";
    String id2="";
    String id3="";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);

        databaseManager = DatabaseManager.getInstance();
        databaseManager.setActivity(this);

        storageManager = StorageManager.getInstance();
        storageManager.setActivity(this);

        flag = false;
        flag2 = false;
        photo =  ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        u = new User("test", "test", "test", "test");
        databaseManager.addDocument("Users", u);
    }

    @Override
    public void updateUIFromDatabase() {
        if(!flag) {
            flag = true;
            p = new Pet(u.getID(), "roza", "female", "asdsa", "3 years", "adasd", "adas");
            databaseManager.addDocument("Pets", p);
        }

        else if(!flag2)
        {
            flag2 = true;
            id1 = storageManager.generateID();
            storageManager.uploadPhoto(photo, "Pets", p.getID(), false, id1);
            id2 = storageManager.generateID();
            storageManager.uploadPhoto(photo, "Pets", p.getID(), false, id2);
            id3 = storageManager.generateID();
            storageManager.uploadPhoto(photo, "Pets", p.getID(), true, id3);
            p.addPhoto(id1);
            p.addPhoto(id2);
            databaseManager.updateDocument("Pets", p.getID(), "photos", p.getPhotos());
        }

    }

    @Override
    public void updateUIFromAuthentication() {

    }

    @Override
    public void updateUIFromStorage() {

    }
}
