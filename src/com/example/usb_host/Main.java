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
	private int allInterfaceNumber = 0;
	
	@SuppressLint("NewApi")
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
		Intent third = new Intent(this, Third.class);
		
		UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
		HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
		Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
		Intent i = new Intent(this, Second.class);
		Bundle data = new Bundle();
		// /*
		int howMany = deviceList.size();
		data.putInt("howMany", howMany);
		
		int k=1;
		allInterfaceNumber=0;
		while (deviceIterator.hasNext()) {
		    UsbDevice device = deviceIterator.next();
		    data.putString("device" + k + "Name", device.getDeviceName());
		    int temp = device.getInterfaceCount();
		    allInterfaceNumber += temp;
		    data.putInt("device" + k + "InterfaceCount", temp);
		    k++;
		    }
		data.putInt("allInterfaceNumber", allInterfaceNumber);
		
		
		// */
		
	/*	
		int howMany = 1;
		data.putInt("howMany", howMany);
		data.putString("device" + 1 + "Name", "USB DEVICE NO1");
	    data.putInt("device" + 1 + "InterfaceCount", 2);
	    
		//PROSTY I SKUTECZNY TEST NA DZIALANIE SECOND ACTIVITY
	*/   
	   	i.putExtras(data);
	   	i.putExtra("deviceList", deviceList);
	   	//third.putExtras(data);
	    //third.putExtra("deviceList", deviceList);
	   	sendBroadcast(third);
		startActivityForResult(i, 1);
	}
}
