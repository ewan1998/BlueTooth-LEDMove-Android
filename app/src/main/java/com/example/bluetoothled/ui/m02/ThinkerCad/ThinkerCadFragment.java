package com.example.bluetoothled.ui.m02.ThinkerCad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bluetoothled.MainViewModel;
import com.example.bluetoothled.databinding.FragmentM02ThinkercadBinding;

public class ThinkerCadFragment extends Fragment {

    private FragmentM02ThinkercadBinding binding;

    ThinkerCadViewModel Model;
    MainViewModel mainModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Model = new ViewModelProvider(this).get(ThinkerCadViewModel.class);
        mainModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding = FragmentM02ThinkercadBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}