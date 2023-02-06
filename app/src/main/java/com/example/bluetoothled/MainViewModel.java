package com.example.bluetoothled;

import android.bluetooth.BluetoothDevice;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bluetoothled.util.Firmata;
import com.example.bluetoothled.util.FirmataVersionData;

import me.aflak.bluetooth.Bluetooth;
import me.aflak.bluetooth.interfaces.DeviceCallback;

public class MainViewModel extends ViewModel {
    static final int JoystickX =14;
    static final int JoystickY= 15;
    static final int JoystickZ= 16;


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
   Bluetooth bluetooth;


  public Firmata firmata;
   public MutableLiveData<String> bluetoothStatus = new MutableLiveData<>();
   public MutableLiveData<FirmataVersionData> firmataVersionData = new MutableLiveData<>();
   public MutableLiveData<Integer> JoysX = new MutableLiveData<>();
   public MutableLiveData<Integer> JoysY = new MutableLiveData<>();
   public MutableLiveData<Integer> JoysZ = new MutableLiveData<>();

   public MainViewModel(){
       bluetoothStatus.setValue("Disconnected");
       firmataVersionData.setValue(new FirmataVersionData());
   }

   void setBluetooth(Bluetooth bluetooth){
       this.bluetooth = bluetooth;
       firmata =new Firmata(this.bluetooth);

       bluetooth.setDeviceCallback(new DeviceCallback() {
           @Override
           public void onDeviceConnected(BluetoothDevice device) {
               bluetoothStatus.postValue("Connected");

           }
           @Override
           public void onDeviceDisconnected(BluetoothDevice device, String message) {
               bluetoothStatus.postValue("Disconnected");
           }

           @Override
           public void onMessage(byte[] message) {
               firmata.processInput(message); //데이터가 들어오면 퍼메타가 판단하도록 프로세스 인풋함

           }

           @Override
           public void onError(int errorCode) {
               bluetoothStatus.postValue("Error");

           }

           @Override
           public void onConnectError(BluetoothDevice device, String message) {
               bluetoothStatus.postValue("Connect Error");

           }
       });

       firmata.attach((m,n)->{
           FirmataVersionData version = new FirmataVersionData();
           version.setMajor(m);
           version.setMinor(n);
           firmataVersionData.postValue(version);
       });

       firmata.attach(Firmata.ANALOG_MESSAGE, (pin, value) -> {
           switch (pin) {
               case JoystickX%14:
                   int Xvalue = Firmata.map(value,0,1023,-360,360);
                   JoysX.postValue(Xvalue);
                   break;
               case JoystickY%14:
                   int Yvalue = Firmata.map(value,0,1023,-360,360);
                   JoysY.postValue(Yvalue);
                   break;
               case JoystickZ%14:
                   JoysZ.postValue(value);
                   break;
           }
       });


   }
    public Bluetooth getBluetooth(){

       return bluetooth;
   }

}
