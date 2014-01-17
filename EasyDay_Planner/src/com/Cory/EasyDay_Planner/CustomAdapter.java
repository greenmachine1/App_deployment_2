package com.Cory.EasyDay_Planner;



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



// my custom adapter
public class CustomAdapter extends ArrayAdapter<Event_With_Custom_Adapter>{
	
	Context context;
	int layoutResourceId;
	Event_With_Custom_Adapter data[] = null;

	public CustomAdapter(Context context, int layoutResourceId, Event_With_Custom_Adapter[] data) {
		super(context, layoutResourceId, data);
		
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
		
	}
	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		
		View row = convertView;
		Event_With_Custom_Adapter_holder holder = null;
		
		if(row == null){
			
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			
			holder = new Event_With_Custom_Adapter_holder();
			holder.imgIcon = (ImageView)row.findViewById(R.id.icon);
			holder.txtTitle = (TextView)row.findViewById(R.id.txt_title);
			holder.txtExtra = (TextView)row.findViewById(R.id.txt_extra);
			
			row.setTag(holder);
			
			
		}else{
			
			holder = (Event_With_Custom_Adapter_holder)row.getTag();
			
		}
		
		Event_With_Custom_Adapter EWCA = data[position];
		holder.txtTitle.setText(EWCA.title);
		holder.imgIcon.setImageResource(EWCA.icon);
		holder.txtExtra.setText(EWCA.extraInfo);
		
		
		return row;
		
	}
	static class Event_With_Custom_Adapter_holder{
		
		ImageView imgIcon;
		TextView txtTitle;
		TextView txtExtra;
		
	}
	


}

