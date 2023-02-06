package com.example.bluetoothled.ui.m03.Wokwi;

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
import com.example.bluetoothled.databinding.FragmentM03WokwiBinding;

public class WokwiFragment extends Fragment {

    private FragmentM03WokwiBinding binding;
    WokwiViewModel Model;
    MainViewModel mainModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Model = new ViewModelProvider(this).get(WokwiViewModel.class);
        mainModel= new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding = FragmentM03WokwiBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}