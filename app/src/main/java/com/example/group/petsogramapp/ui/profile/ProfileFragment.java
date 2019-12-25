package com.example.group.petsogramapp.ui.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.group.petsogramapp.AccountActivity;
import com.example.group.petsogramapp.AddNewPetActivity;
import com.example.group.petsogramapp.R;

import com.example.group.petsogramapp.SettingsActivity;
import com.example.group.petsogramapp.ui.notifications.NotificationsViewModel;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    Activity context;
    Button settingsButton;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        final TextView userNameTextView = root.findViewById(R.id.userNameTextView);
        final Button addNewPetButton = root.findViewById(R.id.addNewPetButton);
        final TextView bioTextView = root.findViewById(R.id.bioTextView);
        final ListView petsListView = root.findViewById(R.id.petsListView);
        settingsButton = root.findViewById(R.id.settingsButton);

        ArrayList<SubjectData> arrayList = new ArrayList<SubjectData>();
        String catsNames[] = {"Ziko", "Roza"};
        int[] imageId  = {R.drawable.cat_1, R.drawable.cat_2};
        CustomAdapter adapter = new CustomAdapter(root.getContext(), catsNames, imageId);
        petsListView.setAdapter(adapter);

        addNewPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddNewPetActivity.class);
                startActivity(intent);

            }

        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);

            }

        });

        return root;
    }
}