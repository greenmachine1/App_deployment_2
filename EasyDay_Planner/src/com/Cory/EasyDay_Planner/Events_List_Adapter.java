package com.Cory.EasyDay_Planner;

import java.util.List;

import android.content.ClipData.Item;

public class Events_List_Adapter {
	public int icon;
	public String title;
	public String second_Row;
	public Events_List_Adapter(){
		super();
	}
	
	public Events_List_Adapter(int icon, String title, String second_Row){
		super();
		
		this.icon = icon;
		this.title = title;
		this.second_Row = second_Row;
		
	}
	
}
