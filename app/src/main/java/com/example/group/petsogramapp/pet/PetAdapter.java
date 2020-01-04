package com.example.group.petsogramapp.pet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group.petsogramapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.group.petsogramapp.Backend.Pet;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ViewHolder> {
    private Context mContext;
    private List<Pet> mPets;
    LayoutInflater Inflater;
    View view;

    public PetAdapter(Context mContext, List<Pet> mPets) {
        this.mContext = mContext;
        this.mPets = mPets;
        Inflater = (LayoutInflater.from(mContext));

    }


    @NonNull
    @Override
    public PetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = Inflater.from(mContext).inflate(R.layout.layout_pet_item, parent, false);
        return new PetAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView image;
        public Button btnFollow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        private void isFollowing(String userid, Button btn){

        }
    }
}



