package com.Cory.EasyDay_Planner;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class ListViewWidgetService extends RemoteViewsService{

	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		// TODO Auto-generated method stub
		return new ListViewRemoteViewsFactory(this.getApplicationContext(), intent);
	}

}




class ListViewRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{

	
	private ArrayList<String> itemsInArray = new ArrayList<String>();
	
	
	public ListViewRemoteViewsFactory(Context applicationContext, Intent intent) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}




	// when this service is created, this gets called
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
		Log.i("This is created!!!", "Yes!!");
		
		
		
	}

	@Override
	public void onDataSetChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RemoteViews getLoadingView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RemoteViews getViewAt(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}
	
}