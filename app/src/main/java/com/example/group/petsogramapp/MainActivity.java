package com.example.group.petsogramapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity implements Updatable
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseManager databaseManager = DatabaseManager.getInstance(this);
        User user = new User("123", "123", "123", "123");
        databaseManager.add("Users", user);
        DocumentReference r = FirebaseFirestore.getInstance().collection("Users").document("H06O1q7O3zu5ly03Uste");
        r.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User u = documentSnapshot.toObject(User.class);
                Log.d("testttttt", u.getEmailAddress());
            }
        });
//        PetProfile profile = new PetProfile(user.getID());
//        databaseManager.add("PetProfiles", profile);
    }

    @Override
    public void updateUI() {

    }
}
