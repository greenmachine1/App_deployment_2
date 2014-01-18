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
		
	
		// this info is just temporary
		String[] elements = {"First", "Second", "Third", "fourth", "fifth", "sixth", "seventh"};
		
		// Targeting the elements list
		elementsListView = (ListView)view.findViewById(R.id.elements_list);
		
		String[] firstRowElements = {"Grant Break", "Take a break at 12:15pm", "Do Laundry by 5pm", "Take Fido out for a walk", "Pick up Son at 7pm" };
		String[] secondRowElements = {"13:25 Left", "Alarm set for 10 minutes prior", "Alarm set for 1 hour prior", "At some point walk the dog", "Alarm set for 10 minutes prior"};
		
		// my custom array
		Events_List_Adapter events_data[] = new Events_List_Adapter[]{
				
				// icon, main title, then secondary info
				new Events_List_Adapter(R.drawable.stop_icon, firstRowElements[0], secondRowElements[0]),
				new Events_List_Adapter(R.drawable.go_icon, firstRowElements[1], secondRowElements[1]),
				new Events_List_Adapter(R.drawable.go_icon, firstRowElements[2], secondRowElements[2]),
				new Events_List_Adapter(R.drawable.go_icon, firstRowElements[3], secondRowElements[3]),
				new Events_List_Adapter(R.drawable.go_icon, firstRowElements[4], secondRowElements[4])
				
		};
		
		// the adapter
		adapter = new CustomAdapter(_context,R.layout.elements_row_layout, events_data);
		
		// setting the adapter to the list
		elementsListView.setAdapter(adapter);
		
		return view;
	}




}
