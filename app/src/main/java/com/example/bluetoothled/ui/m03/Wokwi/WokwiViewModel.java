package com.example.bluetoothled.ui.m03.Wokwi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WokwiViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public WokwiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}