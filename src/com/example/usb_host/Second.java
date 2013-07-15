package com.example.usb_host;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Second extends Activity{
	private Bundle data;
	private Intent intent;
	private Intent third;
	private int k=1; /*Activity finishes with the pressed Button (in 'for' loop), 
				then 'k' is transfer to the next activity (Third.java) as index of wanted device*/
	private Bundle InstanceState = null;
	public Bundle getData() {return data;}
	public int getK() {return k;}

	protected void onCreate(Bundle savedInstanceState) {
		InstanceState = savedInstanceState;
		super.onCreate(InstanceState);
		third = new Intent(this, Third.class);
		intent = getIntent();
		data = intent.getExtras();
		
		int howMany = data.getInt("howMany");
		int allInterfaceNumber = data.getInt("allInterfaceNumber");
		Toast.makeText(this, "number of devices: " + howMany + "\nnumber of interfaces: "
							+ allInterfaceNumber, Toast.LENGTH_LONG).show();	
		
		int[] interfaceCount = null;
		String[] DeviceName = null;
		
		Button[] btn = null; 
				
		if (howMany>0) {
			interfaceCount = new int[howMany];
			DeviceName = new String[howMany];
			btn = new Button[howMany];
		}
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		@SuppressWarnings("deprecation")
		LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		p.setMargins(20, 20, 20, 0);
		
		/*
		Button refresh = new Button(this);
		refresh.setText("Click to refresh the device list\nRefreshing doesn't work for now :P");
		refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent back = new Intent(this, Main.class);
            	startActivity(back);
            }
		});
		refresh.setLayoutParams(p);
		layout.addView(refresh);
		*/
		
		TextView numbers = new TextView(this);
		numbers.setText("number of all devices: " + howMany + "\nnumber of all interfaces: " + allInterfaceNumber);
		numbers.setGravity(Gravity.CENTER);
		numbers.setTextSize(10.0f);
		numbers.setLayoutParams(p);
		layout.addView(numbers);
		
		for (k=1; k<=howMany; k++) {
			interfaceCount[k-1] =  data.getInt("device" + k + "InterfaceCount");
			DeviceName[k-1] = data.getString("device" + 1 + "Name");
			
			btn[k-1] = new Button(this);
			btn[k-1].setText("Device name:\n" + DeviceName[k-1] +
						"\nnumber of available interfaces: " +interfaceCount[k-1]);
			btn[k-1].setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	Intent transfer = getIntent();
	            	Bundle data2 = transfer.getExtras();
	            	data2.putInt("whichDevice", getK()-1);
	            	third.putExtras(data2);	            	
	            	startActivityForResult(third, 1);
	            }
			});
			btn[k-1].setLayoutParams(p);
			layout.addView(btn[k-1]);
		}
		
		Button exit = new Button(this);
		exit.setText("Exit");
		exit.setOnClickListener(new View.OnClickListener() {public void onClick(View v) { finishAffinity(); } });
		exit.setGravity(Gravity.CENTER);
		exit.setLayoutParams(p);
		layout.addView(exit);
	
		
		this.setContentView(layout);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
