package com.Cory.EasyDay_Planner;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;


// widget provider update info
public class WidgetProvider extends AppWidgetProvider{
	
	
	
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
		
		final int appWidgetIdsLength = appWidgetIds.length;
		
		for(int i = 0; i < appWidgetIdsLength; i++){
			
			RemoteViews remoteViews = updateWidgetListView(context, appWidgetIds[i]);
			
			appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
			
		}
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
	
	}
	
	private RemoteViews updateWidgetListView(Context context, int appWidgetId){
		
		
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
		
		
		// start the intent that starts the ListViewService
		Intent intent = new Intent(context, ListViewWidgetService.class);
					
					// put in the app widget ID
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
					
					
		intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
		
		//set the remoteviews object to use a remote views adapter
		// this populates the data
		// note: I had to set min version requirements to 14 (up from 11)
		remoteViews.setRemoteAdapter(R.id.widget_listView, intent);
		
		
		
		// what to display if the data set is empty
		remoteViews.setEmptyView(R.id.widget_listView, R.id.empty_textView);
		
		
		
		
		return remoteViews;
		
		
	}
	
}
	
	
