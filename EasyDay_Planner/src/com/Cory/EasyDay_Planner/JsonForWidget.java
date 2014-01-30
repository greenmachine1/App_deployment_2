package com.Cory.EasyDay_Planner;

import java.io.File;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.Cory.FileManager.FileManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.util.Log;



// my loading json for widget class
public class JsonForWidget{
	
	
	public static JsonForWidget jsonForWidget;
	
	public static int positionPassedIn;
	
	
	FileManager fileManager;
	
	JSONObject mainJSONObject;
	
	JSONArray mainJSONArray;
	
	Context _context;
	
	String finalStringForWidget;
	
	String iconDrawableString;
	
	// creating a hash map for my widget events
	HashMap<String, String> widgetMainEventsHashMap = new HashMap<String, String>();
	HashMap<String, String> iconHashMap = new HashMap<String, String>();

	public JsonForWidget(){
		
	}

	
	public static JsonForWidget getInstance(){
		if(jsonForWidget == null){
			jsonForWidget = new JsonForWidget();
		}
		return jsonForWidget;
	}

	// setting the position to determine which set of JSON Data to return
	public void positionOfData(int position){
		positionPassedIn = position;
			
	}
	

	public void loadJsonData(Context context){
		
		fileManager = new FileManager();
		
		// checking to make sure the file exists
        File file = context.getFileStreamPath("json.txt");
        if(file.exists()){
       	 Log.i("this file exists", "yes");
       	 
       	String mainJsonString = fileManager.readStringFile(context, "json.txt");
       	 
       	 try {
				mainJSONObject = new JSONObject(mainJsonString);
				
				mainJSONArray = mainJSONObject.getJSONArray("main");

				widgetMainEventsHashMap.clear();
				
				iconHashMap.clear();
				
				for(int i = 0; i < mainJSONArray.length(); i ++){
					
					JSONObject c = mainJSONArray.getJSONObject(i);
					
					
					// amazing! Figured out how to grab the name!
					String nameOfEventStringThing = c.names().toString();
				
					// removing [ and ] from the name
					String nameMinusBeginning = nameOfEventStringThing.replace("[\"", "");
					String nameMinusBeginningAndEnd = nameMinusBeginning.replace("\"]", "");

					
					// using the name to get the next event
					JSONObject nameOfEvent = c.getJSONObject(nameMinusBeginningAndEnd);
					
					iconDrawableString = nameOfEvent.getString("icon");
					
					// deciding what info gets injected into my hash map
					// name of event, category, event time
					if(positionPassedIn == 0){
						
						String nameOfEventString = nameOfEvent.getString("name_of_event").toString();
						String categoryForEventString = nameOfEvent.getString("category").toString();
						String timeOfEventString = nameOfEvent.getString("time_of_event").toString();
						
						finalStringForWidget = nameOfEventString + " " + categoryForEventString + " " + timeOfEventString;

					// name of event, event time	
					}else if (positionPassedIn == 1){
						
						String nameOfEventString = nameOfEvent.getString("name_of_event").toString();
						String timeOfEventString = nameOfEvent.getString("time_of_event").toString();
						
						finalStringForWidget = nameOfEventString + " " + timeOfEventString;
						
					// name of event, category	
					}else if (positionPassedIn == 2){
					
						String nameOfEventString = nameOfEvent.getString("name_of_event").toString();
						String categoryForEventString = nameOfEvent.getString("category").toString();
						
						finalStringForWidget = nameOfEventString + " " + categoryForEventString;
						
					}
					
					
					// loading all of this into my hashmap
					widgetMainEventsHashMap.put("" + i, finalStringForWidget);
					iconHashMap.put("" + i, iconDrawableString);
					
					Log.i("final widget String", finalStringForWidget);
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        else if(!(file.exists())){
       	 Log.i("File does not exist", "nope");
        }
		

		
		
	}
	
	// returns the final string
	public String returnedString(){
		
		return finalStringForWidget;
	}
	
	
	
	
	
	
}
