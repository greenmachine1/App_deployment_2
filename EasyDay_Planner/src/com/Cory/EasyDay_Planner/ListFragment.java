package com.Cory.EasyDay_Planner;

import java.io.File;

import org.json.JSONArray;
import org.json.JSONObject;

import com.Cory.FileManager.FileManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

public class ListFragment extends Fragment {
	
	ListView elementsListView;
	//ArrayAdapter<String> adapter;
	
	CustomAdapter adapter;
	
	Context _context;
	
	String fileName = "json.txt";
	
	FileManager fileManager;
	
	String JsonString;
	

	String[] firstRowElements = {"Grant Break", "Take a break at 12:15pm", "Do Laundry by 5pm", "Take Fido out for a walk", "Pick up Son at 7pm"};
	String[] secondRowElements = {"13:25 Left", "Alarm set for 10 minutes prior", "Alarm set for 1 hour prior", "At some point walk the dog", "Alarm set for 10 minutes prior" };

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		fileManager = new FileManager();
		
		
		
		// getting the file json.txt
		File file = getActivity().getFileStreamPath(fileName);
		
		// if it exists...
		if(file.exists()){
			
			// ... load the json data
			loadJsonData();
			
		// ... and if it doesnt	
		}else if(!(file.exists())){
			Log.i("no file exists", "true");
		}
			
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	
	
	
	// when the activity comes back into focus, it brings back up the contents of the file
	// and loads them back into json format
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onPause();
		
		// getting the file json.txt
		File file = getActivity().getFileStreamPath(fileName);
		
		// if it exists...
		if(file.exists()){
					
			// ... load the json data
			loadJsonData();
			
					
		// ... and if it doesnt	
		}else if(!(file.exists())){
					
		}
		
	}
	
	
	
	
	
	// loads in the json data from the file
	public void loadJsonData(){
		
		// reading the file and putting it into json format
		JsonString = fileManager.readStringFile(getActivity(), fileName);

		try{
			
			JSONObject mainJsonObject = new JSONObject(JsonString);
			JSONArray mainJsonArray = mainJsonObject.getJSONArray("main");
			
			Log.i("The contents of the file are", mainJsonArray.toString());
			
			Log.i("elements of the mainJsonArray", "" + mainJsonArray.length());
			
			
			for(int i = 0; i < mainJsonArray.length(); i ++){
				
				JSONObject c = mainJsonArray.getJSONObject(i);
				
				JSONObject nameOfEvent = c.getJSONObject("" + i);
				
				String nameOfEventString = nameOfEvent.getString("name_of_event").toString();
				String noteForEventString = nameOfEvent.getString("note_for_event").toString();
				
				// storing the elements
				firstRowElements[i] = nameOfEventString;
				//secondRowElements[i] = noteForEventString;
				
				Log.i("elements", firstRowElements[i].toString());
				//Log.i("second row", secondRowElements[i].toString());
				
				adapter.notifyDataSetChanged();
						
			}
			
		}catch(Exception e){
			
		}

		

	}
	
	


	
	
	
	



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		Log.i("OnCreateView for fragment", "Got created");
		
		_context = getActivity();
		
		View view;
		
		// inflating the elements_list_fragment xml
		view = inflater.inflate(R.layout.elements_list_fragment, container, false);
		

		// Targeting the elements list
		elementsListView = (ListView)view.findViewById(R.id.elements_list);
		
		// my events data
		Events_List_Adapter events_data[] = new Events_List_Adapter[]{
				
			
			new Events_List_Adapter(R.drawable.stop_icon, firstRowElements[0], secondRowElements[0]),
			new Events_List_Adapter(R.drawable.go_icon, firstRowElements[1], secondRowElements[1]),
			new Events_List_Adapter(R.drawable.go_icon, firstRowElements[2], secondRowElements[2]),
			new Events_List_Adapter(R.drawable.go_icon, firstRowElements[3], secondRowElements[3])
			
		};
			
		// the adapter	
		adapter = new CustomAdapter(_context,R.layout.elements_row_layout, events_data);
		

		
		// setting the adapter to the list
		elementsListView.setAdapter(adapter);
		elementsListView.setOnItemClickListener(new OnItemClickListener(){

			
			// user clicks on an event and sends them to the details activity
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				// starting the Event Details activity
				Intent intent = new Intent(getActivity(), Event_Details.class);
				intent.putExtra("position", position);
				startActivity(intent);
			}
			
		});

			
		
		return view;
		
	}	
	
	
}
