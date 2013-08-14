package com.essentialab.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

	// The activity is being created.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showMsg("Activity Created");
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
 		Log.i("Main Activity State", txt);
 	}
 	
 	// Start Activity A
 	public void startActivityA(View view){
 		Intent intent = new Intent(this, ActivityA.class);
 		intent.putExtra("name", "Activity A");
 		startActivity(intent);
 	}
 	
 	// Start Activity B
  	public void startActivityB(View view){
  		Intent intent = new Intent(this, ActivityB.class);
  		intent.putExtra("name", "Activity B");
  		startActivity(intent);
  	}
 	
}
