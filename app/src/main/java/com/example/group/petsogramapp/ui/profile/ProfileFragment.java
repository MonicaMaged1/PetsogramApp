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

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private Context mContext;
    private Activity mActivity;

    private LinearLayout mLinearLayout;
    private Button mButton;

    private PopupWindow mPopupWindow;
    GridView profileGridView;
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
        mButton = (Button) mActivity.findViewById(R.id.moreInfoButton);





        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.popup_window,null);
                mPopupWindow = new PopupWindow(
                        customView,
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT
                );
                // Set an elevation value for popup window
                // Call requires API level 21
                if(Build.VERSION.SDK_INT>=21){
                    mPopupWindow.setElevation(5.0f);
                }

                // Get a reference for the custom view close button
                ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);

                // Set a click listener for the popup window close button
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        mPopupWindow.dismiss();
                    }
                });
                mPopupWindow.showAtLocation(mLinearLayout, Gravity.CENTER,0,0);



            }
        });



    }
}