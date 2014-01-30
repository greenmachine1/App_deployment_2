package com.Cory.EasyDay_Planner;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;


// widget provider update info
public class WidgetProvider extends AppWidgetProvider{
	
	/*
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
		
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
		
		//remoteViews.setTextViewText(R.id.widget_text, "updated!");
		
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
	}
	
	public void onDeleted(Context context, int[] appWidgetIds){
		
		
	}
	*/
	
	// on update method 
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
		
		// start the intent that starts the ListViewService
		Intent intent = new Intent(context, ListViewWidgetService.class);
		
		// put in the app widget ID
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds);
		intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
		
		// instantiate the remote view for the app widget layout
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
		
		//set the remoteviews object to use a remote views adapter
		// this populates the data
		// note: I had to set min version requirements to 14 (up from 11)
		remoteViews.setRemoteAdapter(R.id.widget_listView, intent);
		
		// what to display if the data set is empty
		remoteViews.setEmptyView(R.id.widget_listView, R.id.empty_textView);
		
		// set it to update
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
		
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
	}
	
	
	// what happens when the app widget is deleted
	public void onDeleted(Context context, int[] appWidgetIds){
		
		
	}
	
	
	
	
	

}
