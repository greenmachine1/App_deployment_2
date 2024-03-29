package com.Cory.EasyDay_Planner;

import java.io.File;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.RemoteViews;

import com.Cory.EasyDay_Planner.Custom_Dialog_ListView.Custom_Dialog_ListView_Listener;
import com.Cory.FileManager.FileManager;



// configuration for the widget
public class WidgetConfig extends FragmentActivity implements Custom_Dialog_ListView_Listener{
	
	FileManager fileManager;
	
	FragmentManager fm = getSupportFragmentManager();
	
	JSONObject mainJSONObject;
	
	JSONArray mainJSONArray;
	
	Context context;
	
	String finalStringForWidget;
	
	String iconDrawableString;
	
	// creating a hash map for my widget events
	HashMap<String, String> widgetMainEventsHashMap = new HashMap<String, String>();
	HashMap<String, String> iconHashMap = new HashMap<String, String>();



	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	 
         setContentView(R.layout.widget_config_layout);
         
         context = this;
         
         fileManager = new FileManager();

         // launching the custom dialog
         Custom_Dialog_ListView customDialogListView = new Custom_Dialog_ListView();
         customDialogListView.show(fm, "ListViewFrag");
         

	}
	
	
	

	// call back from dialog box
	@Override
	public void onItemClickFromDialog(DialogFragment dialog, int whichItem) {
		// TODO Auto-generated method stub
		Log.i("item clicked", "" + whichItem);

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        
		
		if(extras != null){
			
			// gettin back the widget id
			int widgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, 
					AppWidgetManager.INVALID_APPWIDGET_ID);
			
			if(widgetId != AppWidgetManager.INVALID_APPWIDGET_ID){
				
				
				// calls on the JsonForWidget class which loads up
				// my json data.  It passes in the location of the 
				// listview and passes it in
				
				JsonForWidget jsonForWidget = new JsonForWidget();
				jsonForWidget.positionOfData(whichItem);
				jsonForWidget.loadJsonData(this);
				
				int countOfWidgets = jsonForWidget.returnedString().size();
				
				
				
				Log.i("ArrayList from json", jsonForWidget.returnedString().toString() + "" + countOfWidgets);

				
				AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
				
				
				// setting things up in the remote view (widget)
				RemoteViews remoteView = new RemoteViews(this.getPackageName(), R.layout.widget_layout);
				
				appWidgetManager.updateAppWidget(widgetId, remoteView);

				Intent resultValue = new Intent();
				
				resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
				

				setResult(RESULT_OK, resultValue);
				

				
				finish();
				
				
			}
			
			
		}

	}
	
}
