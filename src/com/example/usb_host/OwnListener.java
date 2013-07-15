package com.example.usb_host;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class OwnListener implements OnClickListener {
		private Intent transfer;
		private Intent third;
		private final int K;
		private Second s;
		
    	public OwnListener(Intent transferX, Intent thirdX, int KX, Second sX) {
    		transfer = transferX;
    		third = thirdX;
    		K = KX;    		
    		s=sX;
    	}
    	
		public void onClick(View v) {
    	Bundle data2 = transfer.getExtras();
    	data2.putInt("whichDevice", K);
    	third.putExtras(data2);
    	s.startActivityForResult(third, 1);
		}
}