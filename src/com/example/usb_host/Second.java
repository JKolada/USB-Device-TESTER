package com.example.usb_host;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class Second extends Activity{
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		
		Log.d("whatever","before bundling");
		Bundle bundle = getIntent().getExtras();
		int howMany = bundle.getInt("howMany");
		
		Log.d("whatever","before toasting");
		Toast.makeText(this, "Number of devices  = " + howMany, Toast.LENGTH_LONG).show();	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View view) {this.onRestart();}
	
	public void onClick2(View view) {super.finishAffinity();}
	

}
