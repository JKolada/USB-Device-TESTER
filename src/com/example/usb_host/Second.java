package com.example.usb_host;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class Second extends Activity{
	
	@SuppressWarnings("null")
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		
	   	UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
		HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
		ArrayList<Boolean> permission = null;
		Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
		Intent i = new Intent("com.example.second");
		int howMany=0;
		while(deviceIterator.hasNext()){
		    UsbDevice device = deviceIterator.next();
		    //if (manager.hasPermission(device) == false) manager.requestPermission(device, new PendingIntent()); //???
		    //permission.add(manager.hasPermission(device)); //no need
		    i.putExtra(howMany + "deviceName", device.getDeviceName());
		}
        
		
        // permission.get(0).toString() - permission zerowego device'a w stringu
        
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View view) {
		this.onRestart();
	}
	
	public void onClick2(View view) {
		super.finishAffinity();		
	}
	

}
