package com.mycompany.androidmicrophone;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

public class MicrophoneService extends Service {

	private boolean available = false;

	private static String mFileName = null;
	private MediaRecorder mRecorder = null;

	private final IBinder mBinder = new MyBinder();

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		getMicrophoneInstance();

		if (available == true) {
			Log.i("MicrophoneService", "Microphone Not in Use");
		} else {
			Log.i("MicrophoneService", "Microphone in Use");
		}
		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return mBinder;
	}

	public class MyBinder extends Binder {
		MicrophoneService getService() {
			return MicrophoneService.this;
		}
	}

	/**
	 * get an instance of the MediaRecorder object.
	 **/

	public void getMicrophoneInstance() {

		available = true;

		mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
		mFileName += "/audiorecordtest.3gp";

		// attempt to get a Microphone instance

		mRecorder = new MediaRecorder();

		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
		mRecorder.setOutputFile(mFileName);

		try {
			mRecorder.prepare();
			mRecorder.start();
			mRecorder.release();

		} catch (Exception e) {
			// Microphone is not available (in use or does not exist)
			available = false;
		}

	}

}
