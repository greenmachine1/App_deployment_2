package com.Cory.EasyDay_Planner;

import java.io.File;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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


	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	 
         setContentView(R.layout.widget_config_layout);
         
         context = this;
         
         fileManager = new FileManager();
         
         // checking to make sure the file exists
         File file = this.getFileStreamPath("json.txt");
         if(file.exists()){
        	 Log.i("this file exists", "yes");
        	 
        	String mainJsonString = fileManager.readStringFile(this, "json.txt");
        	 
        	 try {
				mainJSONObject = new JSONObject(mainJsonString);
				
				Log.i("main json object", mainJSONObject.toString());
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
         else if(!(file.exists())){
        	 Log.i("File does not exist", "nope");
         }

         
         
         
         // launching the custom dialog
         Custom_Dialog_ListView customDialogListView = new Custom_Dialog_ListView();
         customDialogListView.show(fm, "ListViewFrag");
         
         
	}

	// call back from dialog box
	@Override
	public void onItemClickFromDialog(DialogFragment dialog, int whichItem) {
		// TODO Auto-generated method stub
		Log.i("item clicked", "" + whichItem);
	}
	
}
