package com.Cory.EasyDay_Planner;

import java.io.File;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.Cory.EasyDay_Planner.Custom_Dialog_ListView.Custom_Dialog_ListView_Listener;
import com.Cory.FileManager.FileManager;



// configuration for the widget
public class WidgetConfig extends FragmentActivity implements Custom_Dialog_ListView_Listener{
	
	FileManager fileManager;
	
	FragmentManager fm = getSupportFragmentManager();
	
	JSONObject mainJSONObject;
	
	JSONArray mainJSONArray;
	
	Context context;
	
	String finalStringForWidget;
	
	String iconDrawableString;
	
	// creating a hash map for my widget events
	HashMap<String, String> widgetMainEventsHashMap = new HashMap<String, String>();
	HashMap<String, String> iconHashMap = new HashMap<String, String>();


	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	 
         setContentView(R.layout.widget_config_layout);
         
         context = this;
         
         fileManager = new FileManager();

         // launching the custom dialog
         Custom_Dialog_ListView customDialogListView = new Custom_Dialog_ListView();
         customDialogListView.show(fm, "ListViewFrag");

	}
	
	
	
	public void getJSONInfo(int position){
		
		// checking to make sure the file exists
        File file = this.getFileStreamPath("json.txt");
        if(file.exists()){
       	 Log.i("this file exists", "yes");
       	 
       	String mainJsonString = fileManager.readStringFile(this, "json.txt");
       	 
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
					if(position == 0){
						
						String nameOfEventString = nameOfEvent.getString("name_of_event").toString();
						String categoryForEventString = nameOfEvent.getString("category").toString();
						String timeOfEventString = nameOfEvent.getString("time_of_event").toString();
						
						finalStringForWidget = nameOfEventString + " " + categoryForEventString + " " + timeOfEventString;

					// name of event, event time	
					}else if (position == 1){
						
						String nameOfEventString = nameOfEvent.getString("name_of_event").toString();
						String timeOfEventString = nameOfEvent.getString("time_of_event").toString();
						
						finalStringForWidget = nameOfEventString + " " + timeOfEventString;
						
					// name of event, category	
					}else if (position == 2){
					
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

	// call back from dialog box
	@Override
	public void onItemClickFromDialog(DialogFragment dialog, int whichItem) {
		// TODO Auto-generated method stub
		Log.i("item clicked", "" + whichItem);
		
		Bundle extras = getIntent().getExtras();
		
		// getting my json data
		getJSONInfo(whichItem);
		
		// getting an output of my widgetMainEventsHashMap
		// and my icon hash
		for(int j = 0; j < widgetMainEventsHashMap.size(); j++){
			
			Log.i("hash", widgetMainEventsHashMap.get("" + j).toString());
			Log.i("icon hash", iconHashMap.get("" + j).toString());
		}
		

		
		if(extras != null){
			
			// gettin back the widget id
			int widgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, 
					AppWidgetManager.INVALID_APPWIDGET_ID);
			
			if(widgetId != AppWidgetManager.INVALID_APPWIDGET_ID){
				
				
				
				
			}
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
