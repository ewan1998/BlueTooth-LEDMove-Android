package com.example.bluetoothled.ui.m02.ThinkerCad;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ThinkerCadViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ThinkerCadViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}