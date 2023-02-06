package com.example.bluetoothled.util;

public interface SysexCallBackFunction {
	void call(byte command, byte argc, byte[] argv);
}
