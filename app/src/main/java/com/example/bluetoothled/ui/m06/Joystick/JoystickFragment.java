package com.example.bluetoothled.ui.m06.Joystick;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bluetoothled.MainViewModel;
import com.example.bluetoothled.databinding.FragmentM06JoystickBinding;

public class JoystickFragment extends Fragment {
    static final int SYSEX_JOY_STOP	    =0x15;
    static final int SYSEX_JOY_START	    =0x16;
    static final int  SYSEX_JOY_RESET	=0x17;

    private FragmentM06JoystickBinding binding;

    JoystickViewModel Model;
    MainViewModel mainModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Model = new ViewModelProvider(this).get(JoystickViewModel.class);
        mainModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding = FragmentM06JoystickBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainModel.JoysX.observe(getViewLifecycleOwner(), value -> {
            binding.textX.setText(String.format("X= %d",value));
            binding.buttonMove.setRotationX(value);
        });

        mainModel.JoysY.observe(getViewLifecycleOwner(), value -> {
            binding.textY.setText(String.format("Y= %d", value));
            binding.buttonMove.setRotationY(value);
        });


        binding.buttonSync.setOnClickListener(v->{
            mainModel.firmata.sendSysex((byte)SYSEX_JOY_START,(byte)0,new byte[]{});
        });

        binding.buttonStop.setOnClickListener(v->{
            mainModel.firmata.sendSysex((byte)SYSEX_JOY_STOP,(byte)0,new byte[]{});
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}