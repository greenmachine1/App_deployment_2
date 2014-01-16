package com.Cory.EasyDay_Planner;

import android.app.Activity;
import android.os.Bundle;

public class About_This_App extends Activity{
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	 
    	 // calling on my list view fragment
         setContentView(R.layout.about_this_app_layout);
	}
}
