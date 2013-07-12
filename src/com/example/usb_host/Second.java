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
	private int k=1; /*Activity finish with the pressed Button (in 'for' loop), 
						then 'k' is transfer to the next activity (Third.java)*/
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
		Toast.makeText(this, "Number of devices  = " + howMany, Toast.LENGTH_LONG).show();	
		
		int[] interfaceCount = null;
		String[] DeviceName = null;
		
		Button[] btn = null; 
		TextView[] tv = null;
				
		if (howMany>0) {
			interfaceCount = new int[howMany];
			DeviceName = new String[howMany];
			btn = new Button[howMany];
			tv = new TextView[howMany];
		}
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		LayoutParams p = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		
		Button refresh = new Button(this);
		refresh.setText("Click to refresh the device list");
		refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	onRestart();
            }
		});
		refresh.setLayoutParams(p);
		layout.addView(refresh);
		
		for (k=1; k<=howMany; k++) {
			interfaceCount[k-1] =  data.getInt("device" + k + "InterfaceCount");
			DeviceName[k-1] = data.getString("device" + 1 + "Name");
			
			btn[k-1] = new Button(this);
			btn[k-1].setText(DeviceName[k-1]);
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
						
			tv[k-1] = new TextView(this);
			tv[k-1].setText("number of available interfaces: " +interfaceCount[k-1]);
			tv[k-1].setLayoutParams(p);
			tv[k-1].setGravity(Gravity.CENTER);
						
			layout.addView(btn[k-1]);
			layout.addView(tv[k-1]);
		}
		
		Button exit = new Button(this);
		exit.setText("Exit");
		exit.setOnClickListener(new View.OnClickListener() {public void onClick(View v) { finishAffinity(); } });
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

	public void onClick(View view) {this.onCreate(InstanceState);}
	public void onClick2(View view) {super.finishAffinity();}

}
