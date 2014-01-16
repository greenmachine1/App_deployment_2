package com.Cory.EasyDay_Planner;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class ListViewFragment extends ListFragment{

	// my string array
	String[] values;
	String[] secondValues;

	ListView listView;
	
	@Override
	  public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    
	    // creating an array of things
	    values = new String[] { "Grant Break", "Pick up son from School", "Go to break"};
	    secondValues = new String[] { "15 minute", "at 3", "at 6"};
	    
	    
	    // setting an array adapter
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
	        android.R.layout.simple_expandable_list_item_1, values);

	   
	   
	    // setting the adapter to the list
	    setListAdapter(adapter);
	  }

	
	  // used to detect touches for the individual rows
	  @Override
	  public void onListItemClick(ListView l, View v, int position, long id) {
	    // do something with the data
		  
		  Log.i("touched at position ", "" + values[position]);

	  }

}
