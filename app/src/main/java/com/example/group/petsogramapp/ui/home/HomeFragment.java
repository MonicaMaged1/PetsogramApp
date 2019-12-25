package com.example.group.petsogramapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.group.petsogramapp.R;
import com.example.group.petsogramapp.ui.profile.PostDetails;
import com.example.group.petsogramapp.ui.profile.PostImageAdapter;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ListView homeListView= null;

    int[] imageId = {
            R.drawable.cat1,
            R.drawable.cat2,
            R.drawable.cat3,
    };

    public View onCreateView(@NonNull LayoutInflater Inflater, ViewGroup Container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View Root = Inflater.inflate(R.layout.fragment_home, Container, false);

        return Root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        homeListView = (ListView) getActivity().findViewById(R.id.homeList);
        HomeImageAdapter adapter = new HomeImageAdapter(getContext(),imageId);
        homeListView.setAdapter(adapter);



        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
    }
}