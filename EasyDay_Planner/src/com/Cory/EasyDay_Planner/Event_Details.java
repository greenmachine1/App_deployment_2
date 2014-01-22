package com.Cory.EasyDay_Planner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.Cory.FileManager.FileManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Event_Details extends Activity{

	int position;
	
	String fileName = "json.txt";
	
	FileManager fileManager;
	
	String jsonString;
	
	TextView eventTextViewText;
	TextView noteTextViewText;
	TextView categoryTextViewText;
	TextView alarmTextViewText;
	TextView eventTimeTextViewText;
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	 
    	 // calling on my list view fragment
         setContentView(R.layout.event_details);
        
         Bundle extras = getIntent().getExtras();
         position = extras.getInt("position");
         
         eventTextViewText = (TextView)findViewById(R.id.event_name_label);
         noteTextViewText = (TextView)findViewById(R.id.event_note_label);
         categoryTextViewText = (TextView)findViewById(R.id.event_category_label);
         alarmTextViewText = (TextView)findViewById(R.id.event_alarm_label);
         eventTimeTextViewText = (TextView)findViewById(R.id.event_time_label);
         
         
         
         fileManager = new FileManager();
         
         
         // reading the file and putting it into json format
 		jsonString = fileManager.readStringFile(this, fileName);

 		try{
 			
 			JSONObject mainJsonObject = new JSONObject(jsonString);
 			JSONArray mainJsonArray = mainJsonObject.getJSONArray("main");

 			JSONObject c = mainJsonArray.getJSONObject(position);
 				
 			JSONObject nameOfEvent = c.getJSONObject("" + position);
 				
 			String nameOfEventString = nameOfEvent.getString("name_of_event").toString();
 			String noteForEventString = nameOfEvent.getString("note_for_event").toString();
 			String categoryString = nameOfEvent.getString("category").toString();
 			String alarmTime = nameOfEvent.getString("alarm_time").toString();
 			String eventTime = nameOfEvent.getString("time_of_event").toString();
 				
 				
 			Log.i("name of event", nameOfEventString);
 			Log.i("note for event", noteForEventString);
 			Log.i("category", categoryString);
 			
 			eventTextViewText.setText(nameOfEventString);
 			noteTextViewText.setText(noteForEventString);
 			categoryTextViewText.setText(categoryString);
 			alarmTextViewText.setText(alarmTime);
 			eventTimeTextViewText.setText(eventTime);
 			
 				
 				
 			
 		}catch(Exception e){
 				
 		}
         
         
         
	}
}
