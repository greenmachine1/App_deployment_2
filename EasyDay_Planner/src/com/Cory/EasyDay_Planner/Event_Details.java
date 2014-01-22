package com.Cory.EasyDay_Planner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.Cory.FileManager.FileManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Event_Details extends Activity{

	int position;
	
	String fileName = "json.txt";
	
	FileManager fileManager;
	
	String jsonString;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	 
    	 // calling on my list view fragment
         setContentView(R.layout.event_details);
        
         Bundle extras = getIntent().getExtras();
         position = extras.getInt("position");
         
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
 				
 				
 			Log.i("name of event", nameOfEventString);
 			Log.i("note for event", noteForEventString);
 				
 				
 			
 		}catch(Exception e){
 				
 		}
         
         
         
	}
}
