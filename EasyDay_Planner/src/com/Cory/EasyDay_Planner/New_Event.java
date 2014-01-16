package com.Cory.EasyDay_Planner;

import android.app.Activity;
import android.os.Bundle;

public class New_Event extends Activity{

	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	 
    	 // calling on my list view fragment
         setContentView(R.layout.new_event_layout);
	}
}
