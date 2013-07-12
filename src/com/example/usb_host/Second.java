package com.example.usb_host;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Second extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Bundle data = getIntent().getExtras();
		int howMany = data.getInt("howMany");
		Toast.makeText(this, "Number of devices  = " + howMany, Toast.LENGTH_LONG).show();	
		
		int[] interfaceCount = null;
		String[] DeviceName = null;
		
		Button[] btn = null; 
		TextView[] tv = null;
		LinearLayout[] layout = null;
				
		if (howMany>0) {
			interfaceCount = new int[howMany];
			DeviceName = new String[howMany];
			btn = new Button[howMany];
			tv = new TextView[howMany];
			layout= new LinearLayout[howMany];
		}
		
		LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		@SuppressWarnings("deprecation")
		LinearLayout.LayoutParams layoutParams =new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,	LayoutParams.WRAP_CONTENT);
		Button refresh = new Button(getApplicationContext());
		refresh.setText("Click to refresh the device list");
		refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	onRestart();
            }
		});
		LinearLayout refresh_layout = new LinearLayout(getApplicationContext());
		refresh_layout.setOrientation(LinearLayout.VERTICAL);
		refresh_layout.addView(refresh);
		this.setContentView(refresh_layout, layoutParams);
		
		
		for (int k=0; k<howMany; k++) {
			interfaceCount[k] = data.getInt("device" + howMany + "Name");
			DeviceName[k] = data.getString("device" + howMany + "Name");
			
			btn[k] = new Button(getApplicationContext());
			btn[k].setText(DeviceName[k]);
			btn[k].setLayoutParams(params);
			
			tv[k] = new TextView(getApplicationContext());
			tv[k].setText(DeviceName[k]);
			tv[k].setLayoutParams(params);
			
			layout[k].setOrientation(LinearLayout.HORIZONTAL);
			layout[k].addView(btn[k]);
			layout[k].addView(tv[k]);
			
			this.addContentView(layout[k], layoutParams);
		}
		
		Button exit = new Button(getApplicationContext());
		exit.setText("Exit");
		exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	finishAffinity();
            }
		});
		LinearLayout exit_layout = new LinearLayout(getApplicationContext());
		exit_layout.setOrientation(LinearLayout.VERTICAL);
		exit_layout.addView(exit);
		this.addContentView(exit_layout, layoutParams);
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
