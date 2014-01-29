package com.Cory.EasyDay_Planner;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.Cory.FileManager.FileManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Event_Details extends Activity{

	File file;
	
	String position;
	
	String fileName = "json.txt";
	
	FileManager fileManager;
	
	String jsonString;
	
	TextView eventTextViewText;
	TextView noteTextViewText;
	TextView categoryTextViewText;
	TextView alarmTextViewText;
	TextView eventTimeTextViewText;
	
	String nameOfEventString;
	String noteForEventString;
	String categoryString;
	String alarmTime;
	String eventTime;
	
	String removedBrackets;
	
	JSONObject nameOfEvent;
	
	JSONObject mainJsonObject;
	JSONArray mainJsonArray;
	
	
	ArrayList<String> listOfNamesArrayList = new ArrayList<String>();
	
	int arrayPosition;
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	 
    	 file = this.getFileStreamPath("json.txt");
    	 
    	 
    	 // calling on my list view fragment
         setContentView(R.layout.event_details);
        
         
         // getting the position of the event from the Listfragment
         Bundle extras = getIntent().getExtras();
         position = extras.getString("position");
         
         eventTextViewText = (TextView)findViewById(R.id.event_name_label);
         noteTextViewText = (TextView)findViewById(R.id.event_note_label);
         categoryTextViewText = (TextView)findViewById(R.id.event_category_label);
         alarmTextViewText = (TextView)findViewById(R.id.event_alarm_label);
         eventTimeTextViewText = (TextView)findViewById(R.id.event_time_label);
         
         
         // gets the file manager
         fileManager = new FileManager();
         
         
         // reading the file and putting it into json format
 		jsonString = fileManager.readStringFile(this, fileName);

 		try{
 			
 			mainJsonObject = new JSONObject(jsonString);
 			mainJsonArray = mainJsonObject.getJSONArray("main");
 			
 			Log.i("main array", mainJsonArray.toString());
 			
 			
 			// getting the integer value 
 			for(int i = 0; i < mainJsonArray.length(); i++){
 				
 				JSONObject c = mainJsonArray.getJSONObject(i);
 				
 				String namesOfEvents = c.names().toString();
 				
 				// removing [ and ] from the name
				String nameMinusBeginning = namesOfEvents.replace("[\"", "");
				String nameMinusBeginningAndEnd = nameMinusBeginning.replace("\"]", "");
 				
 				listOfNamesArrayList.add(nameMinusBeginningAndEnd);
 				
 				
 				// this gets the position of the event
 				arrayPosition = listOfNamesArrayList.indexOf(position);
 				
 			}

 			
 			nameOfEvent = mainJsonArray.getJSONObject(arrayPosition).getJSONObject(position);

	 		nameOfEventString = nameOfEvent.getString("name_of_event").toString();
	 		noteForEventString = nameOfEvent.getString("note_for_event").toString();
	 		categoryString = nameOfEvent.getString("category").toString();
			alarmTime = nameOfEvent.getString("alarm_time").toString();
 			eventTime = nameOfEvent.getString("time_of_event").toString();
	 				
	 				
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
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.details_view_menu, menu);

        
        return true;
    }
    
    
    
    
    
    // Action bar selection handler
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
		
    	switch (item.getItemId()){
    	
    	// what to do when the edi icon is selected 
    	case R.id.edit_icon:
    		
    		Log.i("edit icon pressed", "True");
    		
    		// starting the New_Event activity and passing along
    		// information about this perticular event
    		Intent addEventIntent = new Intent(this, New_Event.class);
    		
    		addEventIntent.putExtra("from_main_activity", false);
    		addEventIntent.putExtra("position", position);
    		addEventIntent.putExtra("name_of_event", nameOfEventString);
    		addEventIntent.putExtra("note_for_event", noteForEventString);
    		addEventIntent.putExtra("category", categoryString);
    		addEventIntent.putExtra("alarm_time", alarmTime);
    		addEventIntent.putExtra("event_time", eventTime);
    		
			startActivity(addEventIntent);
    		
			
    		return true;
    		
    		
    		
    	
    	// what to do when the delete icon is selected
    	case R.id.delete_icon:

    		Log.i("delete icon pressed", "True");
    		
    		deleteCurrentJson();
    		
    		finish();
    		
    		return true;
    		
    		
    		
    		
    	// what to do when the mark as done overflow is selected	
    	case R.id.mark_as_done_overflow:
    		
    		Log.i("mark as done icon pressed", "True");
			
    		markAsDoneMethod();
    		
    		finish();
    		return true;
    		
    		
    		
    		// what to do when the pause timer overflow is selected 
    	case R.id.pause_timer_overflow:
    		
    		Log.i("pause timer icon pressed", "True");
			
    		
    		finish();
    		return true;
    		
    		
    		
    		// what to do when the share overflow is selected 
    	case R.id.share_overflow:
    		
    		Log.i("share icon pressed", "True");
    		
    		
    		// needs to start a sharing intent
    		Intent intent = new Intent(Intent.ACTION_SEND);
    		intent.setType("*/*");
    		intent.putExtra(intent.EXTRA_TITLE, nameOfEventString);
    		intent.putExtra(intent.EXTRA_TEXT, eventTime + " " + noteForEventString);
			if(intent.resolveActivity(getPackageManager()) != null){
				startActivity(intent);
			}
			
    		return true;
    		
    		default:
    			return super.onOptionsItemSelected(item);
    	}
    	
    }
    
    
    
    // gets the current event and overrides the 
    // icon and sets it to a different color
    public void markAsDoneMethod(){
    	
    	
    	try{
    		
    		
    		mainJsonObject = new JSONObject(jsonString);
 			mainJsonArray = mainJsonObject.getJSONArray("main");
 			
 			JSONObject newJSON = (JSONObject) mainJsonArray.getJSONObject(arrayPosition);
 			
 			// this contains the name
 			String nameOfJSON = mainJsonArray.getJSONObject(arrayPosition).names().toString();
 			
 			// removing [ and ] from the name
			String nameMinusBeginning = nameOfJSON.replace("[", "");
			String nameMinusBeginningAndEnd = nameMinusBeginning.replace("]", "");
 			
 			JSONObject iconReplacementString = newJSON.getJSONObject(position).put("icon", "stop_icon");
 			
 			mainJsonArray.put(arrayPosition, newJSON);
 			
 			Log.i("nameminus stuf", nameMinusBeginningAndEnd);
 			
 			Log.i("array contains", iconReplacementString.toString());
 			
 			Log.i("main array contains", "{\"main\":" + mainJsonArray.toString() + "}");
 			
 			fileManager.writeStringFile(this, "json.txt", "{\"main\":" + mainJsonArray.toString() + "}");
    		
    		
    		
    	}catch(Exception e){
    		
    	}
    	
    	
    	
    	
    	
    }
    
    
    
    
    
    
    // deletes the json data that the user has selected
    public void deleteCurrentJson(){
    	
    	// gathers the json data
    	try{
    		
    		mainJsonObject = new JSONObject(jsonString);
 			mainJsonArray = mainJsonObject.getJSONArray("main");
 			
 			JSONObject newJSONArray = (JSONObject) mainJsonArray.getJSONObject(arrayPosition);
 			newJSONArray.remove(position);
 			
 			// converts it to a string so we can pick through it
 			String arrayToString = mainJsonArray.toString();
 			
 			Log.i("array contains", arrayToString.toString());
 			
 			// making sure that the empty object doesn't appear in the string 
 			// anywhere
 			if(arrayToString.contains(",{}")){
 				
 				arrayToString = arrayToString.replace(",{}","");
 				
 				// Over writing the main file
 				fileManager.writeStringFile(this, "json.txt", "{\"main\":" + arrayToString + "}");
 				
 			}else if(arrayToString.contains("{},")){

 				arrayToString = arrayToString.replace("{},", "");
 				
 				// Over writing the main file
 				fileManager.writeStringFile(this, "json.txt", "{\"main\":" + arrayToString + "}");
 				
 			// this one deletes the file	
 			}else if(arrayToString.contains("[{}]")){
 				Log.i("contains ", "[{}]");
 				Log.i("erasing the file", "yes");
 				
 				// delete the file.  Dont want artifacts to be left over
 				file.delete();
 				
 			}
    		
    	}catch(Exception e){
    		Log.e("error", e.getMessage().toString());
    	}

    }

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		nameOfEvent = null;
		
		listOfNamesArrayList = null;
	}


    
    
    
}


