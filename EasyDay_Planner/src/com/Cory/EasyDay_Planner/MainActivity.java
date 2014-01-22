package com.Cory.EasyDay_Planner;

import java.io.File;

import com.Cory.EasyDay_Planner.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {

	Context _context;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	 
    	 // calling on my list view fragment
         setContentView(R.layout.main);
        
        _context = this;
        
        

    }

    
    
    
    
    
    
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        
        return true;
    }
    

    // Action bar selection handler
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
		
    	switch (item.getItemId()){
    	
    	// what to do when the add event icon is selected 
    	case R.id.add_event:
    		
    		Intent addEventIntent = new Intent(_context, New_Event.class);
    		
    		// gets passed to the new event activity to tell which
    		// activity calls it
    		addEventIntent.putExtra("from_main_activity", true);
			startActivity(addEventIntent);
			
    		return true;
    	
    	// what to do when the search overflow is selected
    	case R.id.search_overflow:
    		Log.i("Action bar", "Search button selected");
    		return true;
    		
    		
    	// what to do when the about this app overflow is selected	
    	case R.id.about_this_app_overflow:
    		
    		Intent aboutThisAppIntent = new Intent(_context, About_This_App.class);
			startActivity(aboutThisAppIntent);
			
    		return true;
    		
    		default:
    			return super.onOptionsItemSelected(item);
    	}
    	
    }
    
    
}
