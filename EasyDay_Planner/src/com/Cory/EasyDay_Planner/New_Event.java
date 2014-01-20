package com.Cory.EasyDay_Planner;

import org.json.JSONObject;

import com.Cory.FileManager.FileManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class New_Event extends Activity implements OnItemSelectedListener{

	
	Spinner categorySpinner;
	Button setAnAlarmButton;
	Button setATimeButton;
	
	EditText nameOfEvent;
	EditText noteText;
	
	String selectedItemFromCategory;
	
	Context _context;
	
	// my file name for saving and editing
	String fileName = "json.txt";
	
	// for saving files
	FileManager newFileManager;


	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	 
    	 _context = this;
    	 
    	 newFileManager = new FileManager();
    	 
    	 // calling on my list view fragment
         setContentView(R.layout.new_event_layout);
         
         // targeting my buttons
         setAnAlarmButton = (Button)findViewById(R.id.alarm_set_button);
         setATimeButton = (Button)findViewById(R.id.time_set_button);
         
         nameOfEvent = (EditText)findViewById(R.id.event_name);
         noteText = (EditText)findViewById(R.id.event_note);
         
         
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
         categorySpinner = (Spinner)findViewById(R.id.category_spinner);
         
         // creation of the array adapter for category dropdown
         ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, 
        		 R.array.category_array_list, 
        		 android.R.layout.simple_spinner_dropdown_item);
         
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         
         categorySpinner.setAdapter(adapter);
         
         categorySpinner.setOnItemSelectedListener(this);
         
         
	}
	
	
	

	
	
	
	
	
	// when the user selects a drop down item
	// this is what happens
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		
		String[] categoryArray = {"Home","Home", "Work", "School", "Other"};
		
		selectedItemFromCategory = categoryArray[position].toString();
		
		Log.i("category", selectedItemFromCategory);
		
	}



	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
		// the default category
		selectedItemFromCategory = "Home";
		
	}
	
	
	
	
	
	
	// menu inflate
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_event_menu, menu);

        
        return true;
    }
	
	
	
	
	
	// Action bar selection handler
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
		
    	switch (item.getItemId()){
    	
    	// what to do when the add event icon is selected 
    	case R.id.done_icon:
    		
    		// writing everything to json format
    		writeJson();
			
    		return true;

    		default:
    			return super.onOptionsItemSelected(item);
    	}
    	
    }
	
	
	
	// writing the json data
	public void writeJson(){
		JSONObject jsonObject = new JSONObject();
		
		try{
			
			jsonObject.put("name_of_event", nameOfEvent.getText().toString());
			jsonObject.put("note_for_event", noteText.getText().toString());
			jsonObject.put("category", selectedItemFromCategory.toString());
			
		}catch(Exception e){
			//e.printStackTrace();
			Log.e("error", e.getMessage().toString());
		}
		
		Log.i("object contains", jsonObject.toString());
		
		// writing the json object to file
		newFileManager.writeStringFile(this, fileName, jsonObject.toString());
		
	}




	
	
	
	
}
