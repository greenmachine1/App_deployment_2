package com.Cory.EasyDay_Planner;

import com.Cory.EasyDay_Planner.R;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

public class MainActivity extends Activity {

	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        
        
        
        
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
    	case R.id.add_event:
    		Log.i("Action bar", "add button selected");
    		return true;
    	
    	case R.id.search_overflow:
    		Log.i("Action bar", "Search button selected");
    		return true;
    	case R.id.about_this_app_overflow:
    		Log.i("Action bar", "about this app button selected");
    		return true;
    		
    		default:
    			return super.onOptionsItemSelected(item);
    	}
    	
    }
    
}
