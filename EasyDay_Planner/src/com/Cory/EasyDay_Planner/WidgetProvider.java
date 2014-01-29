package com.Cory.EasyDay_Planner;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;


// widget provider update info
public class WidgetProvider extends AppWidgetProvider{
	
	
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
		
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
		
		remoteViews.setTextViewText(R.id.widget_text, "updated!");
		
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
	}
	
	public void onDeleted(Context context, int[] appWidgetIds){
		
		
	}
	

}
