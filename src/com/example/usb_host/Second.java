package com.example.usb_host;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Second extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.second);
		
		Bundle data = getIntent().getExtras();
		int howMany = data.getInt("howMany");
		Toast.makeText(this, "Number of devices  = " + howMany, Toast.LENGTH_LONG).show();	
		
		int[] interfaceCount = null;
		String[] DeviceName = null;
		if (howMany>0) {
			interfaceCount = new int[howMany];
			DeviceName = new String[howMany];
		}
		for (int k=0; k<howMany; k++) {
			interfaceCount[k] = data.getInt("device" + howMany + "Name");
			DeviceName[k] = data.getString("device" + howMany + "Name");
		}
		

		//JUST AN EXAMPLE
		
		LayoutParams params = new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		TableLayout layout = new TableLayout(getApplicationContext());
		layout.setOrientation(TableLayout.VERTICAL);
		
		TextView tv = new TextView(getApplicationContext());
		tv.setText("example");
		tv.setLayoutParams(params);
		
		TextView tv2 = new TextView(getApplicationContext());
		tv2.setText("example2");
		tv2.setLayoutParams(params);
		
		TableRow row1 = new TableRow(getApplicationContext());
		row1.addView(tv);
		row1.addView(tv2);
		row1.setActivated(true);
		
		layout.addView(row1);
		TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		//setContentView(layout, layoutParams);
		
		
		//---param for views---
		@SuppressWarnings("deprecation")
		LayoutParams params2 =
		new LinearLayout.LayoutParams(
		LayoutParams.FILL_PARENT,
		LayoutParams.WRAP_CONTENT);
		//---create a layout---
		LinearLayout layout1 = new LinearLayout(this);
		layout1.setOrientation(LinearLayout.VERTICAL);
		//---create a textview---
		TextView tv1 = new TextView(this);
		tv1.setText("This is a TextView");
		tv1.setLayoutParams(params2);
		//---create a button---
		Button btn = new Button(this);
		btn.setText("This is a Button");
		btn.setLayoutParams(params2);
		//---adds the textview---
		layout1.addView(tv1);
		//---adds the button---
		layout1.addView(btn);
		//---create a layout param for the layout---
		@SuppressWarnings("deprecation")
		LinearLayout.LayoutParams layoutParam =
		new LinearLayout.LayoutParams(
		LayoutParams.FILL_PARENT,
		LayoutParams.WRAP_CONTENT );
		this.addContentView(layout1, layoutParam);

		
		
		
		
		Log.d("tag", "after everything");
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
