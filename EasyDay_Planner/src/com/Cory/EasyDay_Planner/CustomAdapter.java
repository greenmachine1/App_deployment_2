package com.Cory.EasyDay_Planner;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Events_List_Adapter>{

	Context context;
	int layoutResourceId;
	Events_List_Adapter data[] = null;
	
	// constructor, takes in the context, the layoutResource
	// and the data
	public CustomAdapter(Context context, int layoutResourceId,
			Events_List_Adapter[] data) {
		super(context, layoutResourceId, data);
		
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		
		View row = convertView;
		Events_List_Adapter_Holder holder = null;
		
		if(row == null){
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			
			holder = new Events_List_Adapter_Holder();
			
			holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
			holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
			holder.secondRow = (TextView)row.findViewById(R.id.second_row);
			
			row.setTag(holder);
		}else{
			
			holder = (Events_List_Adapter_Holder)row.getTag();
			
		}
		
		Events_List_Adapter events_list_adapter = data[position];
		
		holder.txtTitle.setText(events_list_adapter.title);
		holder.secondRow.setText(events_list_adapter.second_Row);
		holder.imgIcon.setImageResource(events_list_adapter.icon);
		
		return row;
		
	}
	
	static class Events_List_Adapter_Holder
	{
		ImageView imgIcon;
		TextView txtTitle;
		TextView secondRow;
	}

}
