package com.essentialab.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.app.Activity;

public class ActivityB extends Activity {
	
	private TextView tv;
	private String name;
	
	// The activity is being created.
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_b);
		showMsg("Activity Created");
		
		// Getting value name from intent
		name = getIntent().getExtras().getString("name");
		
		// Getting text view by id from R.java
		tv = (TextView) findViewById(R.id.b);
		
		// Setting name to text view
		tv.setText(name);
	}
	
	// The activity is about to become visible.
 	@Override
 	protected void onStart() {
 		showMsg("Activity Started");
 		super.onStart();
 	}
 	
 	// The activity has become visible (it is now "resumed").
 	@Override
 	protected void onResume() {
 		showMsg("Activity Resumed");
 		super.onResume();
 		// Restore saved state information
 	}
 	
 	// Another activity is taking focus (this activity is about to be "paused").
 	@Override
 	protected void onPause() {
 		showMsg("Activity Paused");
 		super.onPause();
 		// Save State information
 	}
 	
 	// The activity is no longer visible (it is now "stopped").
 	@Override
 	protected void onStop() {
 		showMsg("Activity Stopped");
 		super.onStop();
 	}
 	
 	// The activity is being re-displayed to the user (the user has navigated back to it).
 	@Override
 	protected void onRestart() {
 		showMsg("Activity Restarted");
 		super.onRestart();
 	}
 	
 	// The activity is about to be destroyed.
 	@Override
 	protected void onDestroy() {
 		showMsg("Activity Destroyed");
 		super.onDestroy();
 	}
  
 	// Shows a message with the state of the activity
 	private void showMsg(String txt){
 		Log.i("Activity B State", txt);
 	}
}
