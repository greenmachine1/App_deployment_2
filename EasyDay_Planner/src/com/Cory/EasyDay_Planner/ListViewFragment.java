package com.Cory.EasyDay_Planner;



import android.app.ListFragment;
import android.os.Bundle;

import android.view.View;

import android.widget.ListView;



public class ListViewFragment extends ListFragment{

	
	@Override
	  public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);

	    Event_With_Custom_Adapter EWCA_data[] = new Event_With_Custom_Adapter[]{
	    		new Event_With_Custom_Adapter(R.drawable.go_icon, "Cloudy", "In California"),
	    		new Event_With_Custom_Adapter(R.drawable.stop_icon, "Cloudy", "In California"),
	    		new Event_With_Custom_Adapter(R.drawable.go_icon, "Cloudy", "In California"),
	    		new Event_With_Custom_Adapter(R.drawable.go_icon, "Cloudy", "In California")
	    };
	    
	    
	    CustomAdapter adapter = new CustomAdapter(getActivity(), R.layout.listview_fragment_layout, EWCA_data);
		
	    
	    
	    
		setListAdapter(adapter);
	    
	    
	    
	  }

	
	  // used to detect touches for the individual rows
	  @Override
	  public void onListItemClick(ListView l, View v, int position, long id) {
	    // do something with the data

		  
		  

	  }

}
