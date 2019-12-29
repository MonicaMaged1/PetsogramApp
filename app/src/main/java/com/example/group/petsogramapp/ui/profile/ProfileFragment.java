package com.example.group.petsogramapp.ui.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import com.example.group.petsogramapp.R;

import android.os.Build;
import android.view.Gravity;
import android.widget.ImageButton;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private Context mContext;
    private Activity mActivity;

    private LinearLayout mLinearLayout;
    private Button editProfile;
    private Button moreInfoButton;
    private GridView profileGridView;
    private ImageView profilePicture;
    private TextView numberOfPosts, numberOfFollowers, numberOfFollowing,petName,biography;
    private String profileID;


    int[] imageId = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
    };

    public View onCreateView(@NonNull LayoutInflater Inflater, ViewGroup Container, Bundle savedInstanceState) {
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        View Root = Inflater.inflate(R.layout.fragment_profile, Container, false);

        profileGridView = (GridView) Root.findViewById(R.id.postGridView);
        PostImageAdapter adapter = new PostImageAdapter(Root.getContext(),imageId);
        profileGridView.setAdapter(adapter);

        profileViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        profileGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View View, int Position, long Id) {
                Intent Intent = new Intent(getActivity(), PostDetails.class);
                Intent.putExtra("image", imageId[Position]);
                startActivity(Intent);
            }
        });
        return Root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity().getApplicationContext();
        mActivity = getActivity();
        mLinearLayout = (LinearLayout) mActivity.findViewById(R.id.profileLinearLayout);
        moreInfoButton = (Button) mActivity.findViewById(R.id.moreInfoButton);
        editProfile =(Button) mActivity.findViewById(R.id.editProfile);
        profilePicture =(ImageView) mActivity.findViewById(R.id.profilePicture);
        numberOfPosts = (TextView) mActivity.findViewById(R.id.numberOfPosts);
        numberOfFollowers = (TextView) mActivity.findViewById(R.id.numberOfFollowers);
        numberOfFollowing = (TextView) mActivity.findViewById(R.id.numberOfFollowing);
        biography = (TextView) mActivity.findViewById(R.id.biography);
        petName= (TextView) mActivity.findViewById(R.id.petName);


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
                View customView = inflater.inflate(R.layout.popup_window,null);

                Intent Intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(Intent);


            }
        });


    }
}