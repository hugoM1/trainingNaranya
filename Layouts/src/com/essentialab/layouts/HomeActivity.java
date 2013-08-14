package com.essentialab.layouts;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class HomeActivity extends Activity {
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    
    // Start startTableLayoutActivity
    public void startTableLayoutActivity(View view){
    	Intent intent = new Intent(this, TableLayoutActivity.class);
    	startActivity(intent);
    }
    
    // Start startRelativeLayoutActivity
    public void startRelativeLayoutActivity(View view){
    	Intent intent = new Intent(this, RelativeLayoutActivity.class);
    	startActivity(intent);
    }
    
    // Start startFrameLayoutActivity
    public void startFrameLayoutActivity(View view){
    	Intent intent = new Intent(this, FrameLayoutActivity.class);
    	startActivity(intent);
    }
}
