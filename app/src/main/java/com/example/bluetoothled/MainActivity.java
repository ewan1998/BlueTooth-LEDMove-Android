package com.example.bluetoothled;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.bluetoothled.util.ByteReader;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bluetoothled.databinding.ActivityMainBinding;

import me.aflak.bluetooth.Bluetooth;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    MainViewModel mainModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainModel= new ViewModelProvider(this).get(MainViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        //메뉴 와 연동 (네비게이션)
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_author, R.id.nav_thinkercad, R.id.nav_wokwi,R.id.nav_proteus, R.id.nav_led, R.id.nav_joystick)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        permissionGranted();
        Bluetooth bluetooth = new Bluetooth(this);//1
        bluetooth.setReader(ByteReader.class); //블루트스 세팅 바이트리더 해줘야됨 //2
        mainModel.setBluetooth(bluetooth); //mainModel에서 받음 connect상태 정보 설정같이함.
        Log.d("###","main.oncreate");

        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mainModel.bluetooth.isConnected())
                    Snackbar.make(view, "HC-06-HW2", Snackbar.LENGTH_LONG)
                            .setAction("Disconnect", v -> {
                                mainModel.bluetooth.disconnect();
                            }).show();
                else
                    Snackbar.make(view, "HC-06-HW2", Snackbar.LENGTH_LONG)
                            .setAction("Connect", v->{
                                mainModel.bluetooth.connectToName("HC-06-HW2");
                            }).show();

            }
        });

        mainModel.bluetoothStatus.observe(this, status->{
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
            if(status.equals("Connected")) {
                mainModel.firmata.sendString(status);
            }
        });

    }

    //3
    @Override
    protected void onStart() {
        super.onStart();
        mainModel.bluetooth.onStart();
    }
    //4
    @Override
    protected void onStop() {
        super.onStop();
        mainModel.bluetooth.onStop();
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    void permissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            requestPermissions(
                    new String[]{
                            android.Manifest.permission.BLUETOOTH,
                            android.Manifest.permission.BLUETOOTH_SCAN,
                            android.Manifest.permission.BLUETOOTH_ADVERTISE,
                            android.Manifest.permission.BLUETOOTH_CONNECT


                    },
                    1);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                    new String[]{
                            android.Manifest.permission.BLUETOOTH

                    },
                    1);
        }
    }
}