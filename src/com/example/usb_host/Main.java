package com.example.usb_host;

import java.util.HashMap;
import java.util.Iterator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressLint("NewApi")
	public void onClick(View view) {
		
		UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
		HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
		Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
		Intent i = new Intent("com.example.Second");
		
		Bundle data = new Bundle();
		int howMany = deviceList.size();
		data.putInt("howMany", howMany);
				
		while (deviceIterator.hasNext()) {
		    UsbDevice device = deviceIterator.next();
		    //PendingIntent mPermissionIntent = PendingIntent.getBroadcast(this, 1, i, 0);
		    //if (manager.hasPermission(device) == false) manager.requestPermission(device, mPermissionIntent); //???
		    data.putString("device" + howMany + "Name", device.getDeviceName());
		    data.putInt("device" + howMany + "InterfaceCount", device.getInterfaceCount());
		    }
		startActivityForResult(i, 1);
	}
}
