package com.example.group.petsogramapp.ui.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.group.petsogramapp.Backend.DatabaseManager;
import com.example.group.petsogramapp.Backend.Pet;
import com.example.group.petsogramapp.Backend.StorageManager;
import com.example.group.petsogramapp.Backend.Updatable;
import com.example.group.petsogramapp.R;

import android.os.Build;
import android.view.Gravity;
import android.widget.ImageButton;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ProfileFragment extends Fragment implements Updatable {

    boolean updatingProfilePicture = true;
    private DatabaseManager databaseManager;
    StorageManager storageManager;

//    private ProfileViewModel profileViewModel;
    private Context mContext;
    private Activity mActivity;

    private LinearLayout mLinearLayout;
    private Button editProfile;
    private Button moreInfoButton;
    private GridView profileGridView;
    private ImageView profilePicture;
    private TextView numberOfPosts, numberOfFollowers, numberOfFollowing, petName, biography;
    private Pet myPet;
    private int loadingCount;
    private int photosCount;

    ArrayList<Bitmap> imageId;

    public View onCreateView(@NonNull LayoutInflater Inflater, ViewGroup Container, Bundle savedInstanceState) {
        View Root = Inflater.inflate(R.layout.fragment_profile, Container, false);


//        profileGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View View, int Position, long Id) {
//                Intent Intent = new Intent(getActivity(), PostDetails.class);
//                Intent.putExtra("image", imageId.get(Position));
//                startActivity(Intent);
//            }
//        });
        return Root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getActivity().getApplicationContext();
        mActivity = getActivity();
        mLinearLayout = (LinearLayout) mActivity.findViewById(R.id.profileLinearLayout);
        moreInfoButton = (Button) mActivity.findViewById(R.id.moreInfoButton);
        editProfile = (Button) mActivity.findViewById(R.id.editProfile);
        profilePicture = (ImageView) mActivity.findViewById(R.id.profilePicture);
        numberOfPosts = (TextView) mActivity.findViewById(R.id.numberOfPosts);
        numberOfFollowers = (TextView) mActivity.findViewById(R.id.numberOfFollowers);
        numberOfFollowing = (TextView) mActivity.findViewById(R.id.numberOfFollowing);
        biography = (TextView) mActivity.findViewById(R.id.biography);
        petName = (TextView) mActivity.findViewById(R.id.petName);
        profileGridView = (GridView) mActivity.findViewById(R.id.postGridView);
        imageId = new ArrayList<>();

        databaseManager = DatabaseManager.getInstance();
        databaseManager.setActivity(this);

        storageManager = StorageManager.getInstance();
        storageManager.setActivity(this);

//        Intent intent = getActivity().getIntent();
//        String ID = intent.getStringExtra("petID");
        databaseManager.findDocument("Pets", "3dINNAHZLpUTcwW85KPq", "Pet");


        moreInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Intent = new Intent(getContext(), MoreInformationActivity.class);
                startActivity(Intent);

            }
        });
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.popup_window, null);

                Intent Intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(Intent);
            }
        });

    }


    public void updateUIFromStorage(){
        if(updatingProfilePicture) {
            profilePicture.setImageBitmap(storageManager.getRetrievedPhoto());
            updatingProfilePicture = false;
        }

        else {
            imageId.add(storageManager.getRetrievedPhoto());
            loadingCount++;
            if (loadingCount == photosCount) {
                PostImageAdapter adapter = new PostImageAdapter(mContext, imageId);
                profileGridView.setAdapter(adapter);
            }
        }
    }

    public void updateUIFromDatabase(){
        myPet = (Pet) databaseManager.getFoundDocument();
        photosCount = myPet.getPhotos().size();
        getFromDatabase();
    }

    @Override
    public void updateUIFromAuthentication() {

    }

    public void getFromDatabase() {
        numberOfPosts.setText(myPet.getPostCount() + " \n Posts");
//        numberOfFollowing.setText(myPet.getFollowingCount() + " \n Followers");
        numberOfFollowers.setText(myPet.getFollowerCount() + " \n Followers");
        biography.setText(myPet.getBiography());
        petName.setText(myPet.getName());
        updatingProfilePicture = true;
        storageManager.retrievePhoto("Pets", myPet.getID(), true, "f5e27663-5656-4882-8f16-022d559ed795");

        for (int i = 0; i < myPet.getPhotos().size(); i++) {
            storageManager.retrievePhoto("Pets", myPet.getID(), false, myPet.getPhotos().get(i));
        }
    }
}