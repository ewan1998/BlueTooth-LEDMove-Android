package com.example.bluetoothled.ui.m05.Led;

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
import com.example.bluetoothled.databinding.FragmentM05LedBinding;

public class LedFragment extends Fragment {
    static final int SYSEX_LED_1	    =0x11;
    static final int SYSEX_LED_2	    =0x22;
    static final int SYSEX_LED_3	    =0x33;
    static final int SYSEX_LED_4	    =0x44;
    static final int SYSEX_LED_5	    =0x55;
    static final int SYSEX_LED_6	    =0x66;
    static final int SYSEX_LED_7	    =0x77;
    static final int SYSEX_LED_8        =0x88;
    static final int SYSEX_LED_RIGHT	=0x81;
    static final int SYSEX_LED_LEFT	    =0x82;
    static final int SYSEX_LED_SEND	    =0x83;
    static final int SYSEX_LED_RESET	=0x84;
    static final int SYSEX_JOY_CON	    =0x15;
    private FragmentM05LedBinding binding;

    LedViewModel Model;
    MainViewModel mainModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Model = new ViewModelProvider(this).get(LedViewModel.class);
        mainModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding = FragmentM05LedBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.cb1.setOnClickListener(v->{
            mainModel.firmata.sendSysex((byte)SYSEX_LED_1,(byte)0,new byte[]{});
        });

        binding.cb2.setOnClickListener(v->{
            mainModel.firmata.sendSysex((byte)SYSEX_LED_2,(byte)0,new byte[]{});
        });

        binding.cb3.setOnClickListener(v->{
            mainModel.firmata.sendSysex((byte)SYSEX_LED_3,(byte)0,new byte[]{});
        });

        binding.cb4.setOnClickListener(v->{
            mainModel.firmata.sendSysex((byte)SYSEX_LED_4,(byte)0,new byte[]{});
        });

        binding.cb5.setOnClickListener(v->{
            mainModel.firmata.sendSysex((byte)SYSEX_LED_5,(byte)0,new byte[]{});
        });

        binding.cb6.setOnClickListener(v->{
            mainModel.firmata.sendSysex((byte)SYSEX_LED_6,(byte)0,new byte[]{});
        });

        binding.cb7.setOnClickListener(v->{
            mainModel.firmata.sendSysex((byte)SYSEX_LED_7,(byte)0,new byte[]{});
        });

        binding.cb8.setOnClickListener(v->{
            mainModel.firmata.sendSysex((byte)SYSEX_LED_8,(byte)0,new byte[]{});
        });

        binding.buttonLeft.setOnClickListener(v->{
            mainModel.firmata.sendSysex((byte)SYSEX_LED_LEFT,(byte)0,new byte[]{});
        });

        binding.buttonRight.setOnClickListener(v->{
            mainModel.firmata.sendSysex((byte)SYSEX_LED_RIGHT,(byte)0,new byte[]{});
        });

        binding.buttonSend.setOnClickListener(v->{
            mainModel.firmata.sendSysex((byte)SYSEX_LED_SEND,(byte)0,new byte[]{});
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}