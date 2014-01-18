package com.Cory.EasyDay_Planner;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListFragment extends Fragment {
	
	ListView elementsListView;
	//ArrayAdapter<String> adapter;
	
	CustomAdapter adapter;
	
	Context _context;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		_context = getActivity();
		
		View view;
		
		// inflating the elements_list_fragment xml
		view = inflater.inflate(R.layout.elements_list_fragment, container, false);
		
		
		
		String[] elements = {"First", "Second", "Third", "fourth", "fifth", "sixth", "seventh"};
		
		// Targeting the elements list
		elementsListView = (ListView)view.findViewById(R.id.elements_list);
		
		
		
		// my custom array
		Events_List_Adapter events_data[] = new Events_List_Adapter[]{
				
				new Events_List_Adapter(R.drawable.go_icon, "First", " 1 "),
				new Events_List_Adapter(R.drawable.go_icon, "Second", " 2 "),
				new Events_List_Adapter(R.drawable.go_icon, "Third", " 3 "),
				new Events_List_Adapter(R.drawable.go_icon, "Forth", " 4 ")
				
		};
		
		// the adapter
		adapter = new CustomAdapter(_context,R.layout.elements_row_layout, events_data);
		
		// setting the adapter to the list
		elementsListView.setAdapter(adapter);
		
		return view;
	}




}
