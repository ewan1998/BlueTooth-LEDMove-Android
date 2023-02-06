package com.example.bluetoothled.ui.m06.Joystick;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class JoystickViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public JoystickViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}