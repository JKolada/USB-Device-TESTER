package com.example.usb_host;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Main extends Activity {

	
	/*
	 * useful www:
	 * http://developer.android.com/reference/android/hardware/usb/UsbManager.html
	 * 
	 * use the "manager" object!
	 * UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
	 */
	
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

	public void onClick(View view) {
		startActivity(new Intent("com.example.Second"));
	}
}
