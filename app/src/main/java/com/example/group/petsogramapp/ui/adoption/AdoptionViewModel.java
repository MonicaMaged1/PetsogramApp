package com.example.group.petsogramapp.ui.adoption;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdoptionViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AdoptionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}