package com.example.group.petsogramapp.ui.adoption;

import android.content.Intent;
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
import com.example.group.petsogramapp.SignUp;
import com.example.group.petsogramapp.ui.profile.ProfileFragment;

public class AdoptionFragment extends Fragment {

    private AdoptionViewModel adoptionViewModel;
    private ListView adoptionListView;
    private Button viewProfileAdoptionButton;

    int[] imageId = {
            R.drawable.cat_2,
            R.drawable.cat_1,

    };

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { adoptionViewModel = ViewModelProviders.of(this).get(AdoptionViewModel.class);
        final View root = inflater.inflate(R.layout.adoption_rows, container, false);
        final View root2 = inflater.inflate(R.layout.fragment_adoption, container, false);
        viewProfileAdoptionButton= (Button) root.findViewById(R.id.viewProfileAdoptionButton);
        adoptionListView = (ListView) root2.findViewById(R.id.adoptionList);
        AdoptionCustomAdapter adapter = new AdoptionCustomAdapter(root.getContext(),imageId);
        adoptionListView.setAdapter(adapter);
        viewProfileAdoptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent_2 = new Intent(getActivity(), ProfileFragment.class);
//                startActivity(intent_2);
            }
        });


        return root;
    }
}