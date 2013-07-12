package com.example.usb_host;

import java.util.HashMap;
import java.util.Iterator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Third extends Activity{
	private String DeviceName = null;
	private UsbDevice TheDevice=null;
	private UsbInterface UsbIntTable[] = null;
	private String UsbIntName[] = null;
	private int usbIntEndCount[] = null;
	private int interfaceCount = 0;
	
	@SuppressLint("NewApi")
	@SuppressWarnings("unchecked")
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("tag", "WARN: POCZATEK ^^^^^^^^^^^^^^^^^");
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		Bundle data = intent.getExtras();
		int whichDevice = data.getInt("whichDevice");
		Toast.makeText(this, "Device nr " + whichDevice , Toast.LENGTH_LONG).show();
		
    	Log.d("tag", "warn: przed intent.getSerializable");
		
		HashMap<String, UsbDevice> deviceList;
		deviceList = (HashMap<String, UsbDevice>) intent.getSerializableExtra("deviceList");
		Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
		
		int k=1;
		UsbDevice device = null;
		
		Log.d("tag", "warn: przed while; deviceList.size() = " + deviceList.size());
		// to sie jeszcze przydasie
		
    	// searching for wanted Device
		
			while (deviceIterator.hasNext()) {
			    device = deviceIterator.next();
			    if (k==whichDevice) {
			    	TheDevice=device;
			    	DeviceName = TheDevice.getDeviceName();
			    	Log.d("tag", "warn: tu go byc nie powinno");
			    	break;
			    }
			    k++;
			}
		if (TheDevice==null) {
			TextView e = new TextView(this);
			LinearLayout layout = new LinearLayout(this);
			layout.setOrientation(LinearLayout.VERTICAL);
			LayoutParams p = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			e.setText("Error: Device = null");
			e.setLayoutParams(p);
			e.setGravity(Gravity.CENTER);
			layout.addView(e);
			this.setContentView(layout);
		}
		else {
			interfaceCount = TheDevice.getInterfaceCount();
			Log.d("tag", "warn: przed inicjacja; interfaceCount = " + interfaceCount);
			
			if (interfaceCount>0) {
				this.UsbIntTable = new UsbInterface[interfaceCount];
				this.UsbIntName = new String[interfaceCount];
				this.usbIntEndCount = new int[interfaceCount];
				for (k=0; k<interfaceCount; k++) {
					UsbIntTable[k] = TheDevice.getInterface(k);
					UsbIntName[k] = UsbIntTable[k].toString();
					usbIntEndCount[k] = UsbIntTable[k].getEndpointCount();
				}
			}
			
	    	Log.d("tag", "warn: przed petla z tablicami UsbInt***");
					
			
			///////////////////////////////////////////////// INTERFACE
			
			LinearLayout layout = new LinearLayout(this);
			layout.setOrientation(LinearLayout.VERTICAL);
			LayoutParams p = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);	
			
			TextView NameTV = new TextView(this);
			NameTV.setText("Device Name: " + DeviceName);
			LayoutParams q = p; 
			// jakos bolda by sie zdalo zrobic
			NameTV.setLayoutParams(q);
			NameTV.setGravity(Gravity.CENTER);
			layout.addView(NameTV);
					
			TextView tv[] = new TextView[interfaceCount];
			for (k=0; k<interfaceCount; k++) {
				tv[k] = new TextView(this);
				tv[k].setText("Name of inteface: " + UsbIntName + "\n		number of endpoints: " + usbIntEndCount[k] +"\n\n");
				tv[k].setLayoutParams(p);
				tv[k].setGravity(Gravity.CENTER);
				layout.addView(tv[k]);
				
				Button btn[] = new Button[usbIntEndCount[k]];
				for (int i=0; i<usbIntEndCount[k]; i++) {
					btn[i] = new Button(this);
					btn[i].setText(UsbIntTable[i].toString());
					btn[i].setOnClickListener(new View.OnClickListener() { public void onClick(View v)
																				{/*tu onCLLICK*/ finishAffinity();}});
			        btn[i].setLayoutParams(p);
			        layout.addView(btn[i]); 
				}
			}
			this.setContentView(layout);
		}
	}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View view) {this.onStop();}
	public void onClick2(View view) {super.finishAffinity();}
}
