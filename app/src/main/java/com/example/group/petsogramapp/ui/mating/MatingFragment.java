package com.example.group.petsogramapp.ui.mating;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.group.petsogramapp.R;
import com.example.group.petsogramapp.ui.adoption.AdoptionCustomAdapter;

public class MatingFragment extends Fragment {

    private MatingViewModel homeViewModel;
    private ListView matingListView;
    private Button viewProfileAdoptionButton;
    private Button filterButton;
    int[] imageId = {
            R.drawable.cat_2,
            R.drawable.cat_1,

    };

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(MatingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mating, container, false);
        matingListView = (ListView) root.findViewById(R.id.matingList);
        filterButton=(Button)root.findViewById(R.id.filterButton);
        MatingCustomAdapter adapter = new MatingCustomAdapter(root.getContext(),imageId);
        matingListView.setAdapter(adapter);

        return root;
    }
}