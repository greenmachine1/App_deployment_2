package com.Cory.EasyDay_Planner;

import java.io.File;

import org.json.JSONArray;
import org.json.JSONObject;

import com.Cory.FileManager.FileManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
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

public class New_Event extends FragmentActivity implements OnItemSelectedListener, TimePickerFragment.OnTimeSetListener{

	
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
	
	JSONObject mainObject;
	JSONArray mainObjectArray;
	
	int arrayLength;
	
	int hourFromPicker = 0;
	int minuteFromPicker = 0;
	
	int positionFromEditActivity;
	
	String nameOfEventFromEdit;
	String noteForEventFromEdit;
	String categoryForEventFromEdit;
	String alarmTimeFromEdit;
	String eventTimeFromEdit;


	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	 
    	 getActionBar().setDisplayHomeAsUpEnabled(true);
    	 
    	 // getting my extras if this activity was called from an 
    	 // edit
    	 /*
    	 Bundle extras = getIntent().getExtras();
         positionFromEditActivity = extras.getInt("position");
         nameOfEventFromEdit = extras.getString("name_of_event");
         noteForEventFromEdit = extras.getString("note_for_event");
         categoryForEventFromEdit = extras.getString("category");
         alarmTimeFromEdit = extras.getString("alarm_time");
         eventTimeFromEdit = extras.getString("event_time");
         */

    	 
    	 
    	 
    	 
    	 _context = this;
    	 
    	 newFileManager = new FileManager();
    	 
    	 mainObject = new JSONObject();
    	 mainObjectArray = new JSONArray();
    	 
    	 
    	 
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
				
				DialogFragment newPicker = new TimePicker();
				newPicker.show(getSupportFragmentManager(), "timePicker");
				
				
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
				
		// the subobject for the main object array
		JSONObject nameOfJsonObject = new JSONObject();
		
		// sub sub object of the main object array
		JSONObject jsonObject = new JSONObject();
		
			// writing the json object to file
			File file = this.getFileStreamPath(fileName);
			
			// portion adds to the exsisting json file
			if(file.exists()){
				Log.i("It is", "There");
				
				// opening up of the json file
				String JSONString = newFileManager.readStringFile(_context, fileName);
				
				// converting the file into json objects and arrays
				JSONObject mainObject = new JSONObject();
				JSONArray mainArrayObject = new JSONArray();
				try{
					
					mainObject = new JSONObject(JSONString);
					
					mainArrayObject = mainObject.getJSONArray("main");
					
					jsonObject.put("name_of_event", nameOfEvent.getText().toString());
					jsonObject.put("note_for_event", noteText.getText().toString());
					jsonObject.put("category", selectedItemFromCategory.toString());
					jsonObject.put("alarm_time", "" + 3);
					jsonObject.put("time_of_event", "" + 4);
					
					// this will be used to set the completeness of the 
					// activity
					// from the event details, the user can select
					// mark as done, which will change this icon to "stop_icon"
					jsonObject.put("icon", "go_icon");
					
					// putting all the elements into a json object...
					
					
					
					nameOfJsonObject.put("" + mainArrayObject.length(), jsonObject);
					
					
					
					
					// ...then putting that object into the main array...
					mainArrayObject.put(nameOfJsonObject);
					
					// ... then putting that object into the main encapsulating
					// object.
					mainObject.put("main", mainArrayObject);
					
					
					
				}catch(Exception e){
					Log.e("error", e.getMessage().toString());
				}
				
			
				// writing it all to a file
				newFileManager.writeStringFile(this, fileName, mainObject.toString());
				
				
				
				
			// if the file does not exist, make the file and put in info	
			}else if(!(file.exists())){
				
				try{
					
					jsonObject.put("name_of_event", nameOfEvent.getText().toString());
					jsonObject.put("note_for_event", noteText.getText().toString());
					jsonObject.put("category", selectedItemFromCategory.toString());
					jsonObject.put("alarm_time", "" + 3);
					jsonObject.put("time_of_event", "" + 4);
					
					// this will be used to set the completeness of the 
					// activity
					// from the event details, the user can select
					// mark as done, which will change this icon to "stop_icon"
					jsonObject.put("icon", "go_icon");
					
					// putting all the elements into a json object...
					nameOfJsonObject.put("" + (mainObjectArray.length()), jsonObject);
					
					// ...then putting that object into the main array...
					mainObjectArray.put(nameOfJsonObject);
					
					// ... then putting that object into the main encapsulating
					// object.
					mainObject.put("main", mainObjectArray);
					
				}catch(Exception e){
					//e.printStackTrace();
					Log.e("error", e.getMessage().toString());
				}

				// writing it all to a file				
				newFileManager.writeStringFile(this, fileName, mainObject.toString());
				
			}
		
	}








	// call back from my time picker
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// TODO Auto-generated method stub
		
		
		
		hourFromPicker = hourOfDay;
		minuteFromPicker = minute;
		
		Log.i("hours and minutes", "" + hourFromPicker + "" + minuteFromPicker);
		
	}




	
	
	
	
}
