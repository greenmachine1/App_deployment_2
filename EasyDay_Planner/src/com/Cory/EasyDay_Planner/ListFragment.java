package com.Cory.EasyDay_Planner;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;


import org.json.JSONArray;
import org.json.JSONObject;

import com.Cory.FileManager.FileManager;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.ListView;

public class ListFragment extends Fragment {
	
	ListView elementsListView;
	
	CustomAdapter adapter = null;
	
	Context _context;
	
	String fileName = "json.txt";
	
	FileManager fileManager;
	
	String JsonString;
	
	JSONArray mainJsonArray;

	HashMap<String, String> firstRowHash = new HashMap<String, String>();
	HashMap<String, String> secondRowHash = new HashMap<String, String>();
	
	ArrayList<String> listOfNames = new ArrayList<String>();
	
	ArrayList<Events_List_Adapter> items = new ArrayList<Events_List_Adapter>(); 
	


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		
		// setting my file manager
		fileManager = new FileManager();
		
		// creating my adapter
		adapter = new CustomAdapter(getActivity(),R.layout.elements_row_layout, items);
		
		// getting the file json.txt
		File file = getActivity().getFileStreamPath(fileName);
		
		//file.delete();
		
		// if it exists...
		if(file.exists()){
			
			// ... load the json data
			loadJsonData();

		// ... and if it doesnt	
		}else if(!(file.exists())){
			Log.i("no file exists", "true");
			adapter.notifyDataSetChanged();
		}

	}
	



	// when the activity comes back into focus, it brings back up the contents of the file
	// and loads them back into json format
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		
		// getting the file json.txt
		File file = getActivity().getFileStreamPath(fileName);
		
		// if it exists...
		if(file.exists()){

			// ... load the json data
			loadJsonData();
			
		// ... and if it doesnt	
		}else if(!(file.exists())){
					
			Log.i("file not found", "true");
			
			items.clear();
			adapter.notifyDataSetChanged();
		}
		
		
		
	}
	
	
	
	
	
	// loads in the json data from the file
	// this reloads everytime the user goes back to the main activity
	public void loadJsonData(){
		
		// reading the file and putting it into json format
		JsonString = fileManager.readStringFile(getActivity(), fileName);
		
		try{

			JSONObject mainJsonObject = new JSONObject(JsonString);
			
			mainJsonArray = mainJsonObject.getJSONArray("main");
			
			Log.i("The contents of the file are", mainJsonArray.toString());
			
			firstRowHash.clear();
			secondRowHash.clear();
			
			// clearing out my items ArrayList to make way for new ones
			items.clear();
			listOfNames.clear();

			for(int i = 0; i < mainJsonArray.length(); i ++){
				
				JSONObject c = mainJsonArray.getJSONObject(i);
				
				
				// amazing! Figured out how to grab the name!
				String nameOfEventStringThing = c.names().toString();
			
				// removing [ and ] from the name
				String nameMinusBeginning = nameOfEventStringThing.replace("[\"", "");
				String nameMinusBeginningAndEnd = nameMinusBeginning.replace("\"]", "");

				
				// using the name to get the next event
				JSONObject nameOfEvent = c.getJSONObject(nameMinusBeginningAndEnd);
				
				String nameOfEventString = nameOfEvent.getString("name_of_event").toString();
				String noteForEventString = nameOfEvent.getString("note_for_event").toString();
				String categoryForEventString = nameOfEvent.getString("category").toString();
				String iconInteger = nameOfEvent.getString("icon").toString();
				
				// decides which category it is
				decideWhichCategory(categoryForEventString);
				
				firstRowHash.put(nameMinusBeginningAndEnd, nameOfEventString);
				secondRowHash.put(nameMinusBeginningAndEnd, noteForEventString);
				
				
				
				// declaring what my Events_List_Adapter will hold
				// this needs to have an array for the icon as well
				Events_List_Adapter item = new Events_List_Adapter(R.drawable.stop_icon, firstRowHash.get(nameMinusBeginningAndEnd).toString(), secondRowHash.get(nameMinusBeginningAndEnd).toString());
					
				// adding that to items
				items.add(item);
				
				listOfNames.add(nameMinusBeginningAndEnd);
				
				// notifying my adapter that its data
				// has changed
				adapter.notifyDataSetChanged();
			}

		}catch(Exception e){
			Log.e("error", e.getMessage().toString());
		}
		
		

	}
	
	
	public void decideWhichCategory(String category){
		
		if(category.equals("Home")){
			
			Log.i("it equals", "Home");
		}
		else if(category.equals("Work")){
			
			Log.i("it equals", "Work");
		}
		else if(category.equals("School")){
			
			Log.i("it equals", "School");
		}
		else if(category.equals("Other")){
			
			Log.i("it equals", "Other");
		}
		
	}

	
	


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.i("onCreateView", "called");
		
		View view;
		
		// inflating the elements_list_fragment xml
		view = inflater.inflate(R.layout.elements_list_fragment, container, false);
		

		// Targeting the elements list
		elementsListView = (ListView)view.findViewById(R.id.elements_list);

		// setting the adapter to the list
		elementsListView.setAdapter(adapter);

		// setting my onItemClickListener for the elementsListView
		elementsListView.setOnItemClickListener(new OnItemClickListener(){

			
			
			
			
			// user clicks on an event and sends them to the details activity
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				// starting the Event Details activity
				Intent intent = new Intent(getActivity(), Event_Details.class);
				
				intent.putExtra("position", listOfNames.get(position).toString());
				
				Log.i("number", "" + position);
				
				Log.i("position", listOfNames.get(position).toString());
				
				startActivity(intent);
			}
			
		});

		return view;
		
	}	

}
