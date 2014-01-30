package com.Cory.EasyDay_Planner;




import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.widget.RemoteViewsService;

public class ListViewWidgetService extends RemoteViewsService{

	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		
		int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		
		// TODO Auto-generated method stub
		return (new WidgetListView(this.getApplicationContext(), intent));
	}

}

