package com.Cory.EasyDay_Planner;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


import org.json.JSONArray;
import org.json.JSONObject;


import com.Cory.EasyDay_Planner.CustomAdapter.Events_List_Adapter_Holder;
import com.Cory.FileManager.FileManager;


import android.content.Context;
import android.content.Intent;
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
	
	ArrayList<Events_List_Adapter> items = new ArrayList<Events_List_Adapter>(); 
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);


	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
		fileManager = new FileManager();
		
		adapter = new CustomAdapter(getActivity(),R.layout.elements_row_layout, items);
		
		// getting the file json.txt
		File file = getActivity().getFileStreamPath(fileName);
		file.delete();
		
		// if it exists...
		if(file.exists()){
			
			// ... load the json data
			loadJsonData();

		// ... and if it doesnt	
		}else if(!(file.exists())){
			Log.i("no file exists", "true");
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
			//adapter.notifyDataSetChanged();
			
			

		// ... and if it doesnt	
		}else if(!(file.exists())){
					
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
			
			Log.i("elements of the mainJsonArray", "" + mainJsonArray.length());
			
			firstRowHash.clear();
			secondRowHash.clear();
			
			items.clear();

			for(int i = 0; i < mainJsonArray.length(); i ++){
				
				JSONObject c = mainJsonArray.getJSONObject(i);
				
				JSONObject nameOfEvent = c.getJSONObject("" + i);
				
				String nameOfEventString = nameOfEvent.getString("name_of_event").toString();
				String noteForEventString = nameOfEvent.getString("note_for_event").toString();
				
				firstRowHash.put("" + i, nameOfEventString);
				secondRowHash.put("" + i, noteForEventString);
				
				
				Events_List_Adapter item = new Events_List_Adapter(R.drawable.go_icon, firstRowHash.get("" + i).toString(), secondRowHash.get("" + i).toString());
					
				// adding that to items
				items.add(item);
				adapter.notifyDataSetChanged();
			}
			
			

		}catch(Exception e){
			Log.e("error", e.getMessage().toString());
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

		

		// this is pretty complicated but it iterates through my hashmaps
		// puts the data into a new Events_List_Adapter, then puts all that 
		// into a List array, then puts it into the adapter
		//items = new ArrayList<Events_List_Adapter>();
		
		// setting the adapter info
		//adapter = new CustomAdapter(getActivity(),R.layout.elements_row_layout, items);
		
		// setting the adapter to the list
		elementsListView.setAdapter(adapter);
		
		/*
		for(int i = 0; i < firstRowHash.size(); i++){
			Events_List_Adapter item = new Events_List_Adapter(R.drawable.go_icon, firstRowHash.get("" + i).toString(), secondRowHash.get("" + i).toString());
			
			// adding that to items
			items.add(item);
			adapter.notifyDataSetChanged();
			
		}
		*/
		
		
		// setting the adapter info
		//adapter = new CustomAdapter(getActivity(),R.layout.elements_row_layout, items);
		
		


		// setting the adapter to the list
		//elementsListView.setAdapter(adapter);
		
		
		elementsListView.setOnItemClickListener(new OnItemClickListener(){

			
			// user clicks on an event and sends them to the details activity
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				// starting the Event Details activity
				Intent intent = new Intent(getActivity(), Event_Details.class);
				intent.putExtra("position", position);
				
				Log.i("position", "" + position);
				startActivity(intent);
			}
			
		});

		return view;
		
	}	
	

	
	
}
