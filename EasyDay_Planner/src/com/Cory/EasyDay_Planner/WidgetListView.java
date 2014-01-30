package com.Cory.EasyDay_Planner;

import java.util.ArrayList;

import android.app.LauncherActivity.ListItem;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

public class WidgetListView implements RemoteViewsFactory{
	
	// creating a list
	private ArrayList<ListItemWidget> listItemList = new ArrayList<ListItemWidget>();
	private Context context = null;
	private int appWidgetId;
	
	RemoteViews remoteView;
	
	public WidgetListView(Context context, Intent intent){
		
		this.context = context;
		appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		
		populateList();
		
		
	}
	
	
	private void populateList(){
		
		// getting my json info
		JsonForWidget newJsonForWidget = new JsonForWidget();
		newJsonForWidget.loadJsonData(context);
		
		int sizeOfJson = newJsonForWidget.returnedString().size();
		
		listItemList.clear();
		
		// iterating through my json data and adding it to a new list of items
		for(int i = 0; i < sizeOfJson; i++){
			
			String tempString = newJsonForWidget.returnedString().get(i);
			
			// splitting the info into seperate chunks to be delegated
			// in the xml file
			ListItemWidget listItem = new ListItemWidget();
			listItem.content = tempString;
			listItem.icon = 10;
			
			// adding them all ot my listItemList
			listItemList.add(listItem);
			
			
		}
		
		
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listItemList.size();
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public RemoteViews getLoadingView() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public RemoteViews getViewAt(int position) {
		
		// targetting my widget items
		remoteView = new RemoteViews(context.getPackageName(),R.layout.widget_item);
		
		ListItemWidget listItem = listItemList.get(position);
		remoteView.setTextViewText(R.id.item_text, listItem.content);
		// this is my icon remoteView.setTextViewText(R.id.item_icon, listItem.icon);
		return remoteView;
		

	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.i("has been created", "Yes");
		
		populateList();
		
	}

	@Override
	public void onDataSetChanged() {
		// TODO Auto-generated method stub
		
		Log.i("has been created", "Yes");
		populateList();
		
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		
	}

}
