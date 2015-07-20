package com.mycompany.androidmicrophone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyStartServiceReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		Log.i("MyStartServiceReceiver", "onReceive"); 
		
		Intent service = new Intent(context, MicrophoneService.class);
		context.startService(service);
	}
}
