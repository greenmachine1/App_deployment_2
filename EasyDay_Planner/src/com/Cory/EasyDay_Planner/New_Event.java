package com.Cory.EasyDay_Planner;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class New_Event extends Activity{

	
	Spinner categorySpinner;
	Button setAnAlarmButton;
	Button setATimeButton;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	 
    	 // calling on my list view fragment
         setContentView(R.layout.new_event_layout);
         
         setAnAlarmButton = (Button)findViewById(R.id.alarm_set);
         setATimeButton = (Button)findViewById(R.id.time_set);
         
         
         // setting the alarm button click listener
         setAnAlarmButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Log.i("alarm button", "Pressed");
			}
        	 
         });
         
         
         // setting the time set click listener
         setATimeButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Log.i("set Time button", "Pressed");
				
			}
        	 
         });
         

         // set category info
         categorySpinner();
         
	}
	
	
	
	// category spinner
	public void categorySpinner(){
		
		
		categorySpinner = (Spinner)findViewById(R.id.category_spinner);
        
        // creation of the array adapter for category dropdown
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, 
       		 R.array.category_array_list, 
       		 android.R.layout.simple_spinner_dropdown_item);
        
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        categorySpinner.setAdapter(adapter);
        
	}
	
	
	
	
	
	
	// menu inflate
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_event_menu, menu);

        
        return true;
    }
	
	
	
	
	public void writeJson(){
		JSONObject jsonObject = new JSONObject();
		
		try{
			
			jsonObject.put("name", "My name is Cory");
			jsonObject.put("age", "29");
			jsonObject.put("category", "home");
			
		}catch(Exception e){
			//e.printStackTrace();
			Log.e("error", e.getMessage().toString());
		}
		
		Log.i("object contains", jsonObject.toString());
	}
}
