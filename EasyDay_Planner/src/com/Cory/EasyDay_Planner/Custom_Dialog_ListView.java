package com.Cory.EasyDay_Planner;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

public class Custom_Dialog_ListView extends DialogFragment{
	
	ArrayList<String> widgetList = new ArrayList<String>();
	
	// this defines the method that must be used for 
	// Custom_Dialog_ListView to be used
	public interface Custom_Dialog_ListView_Listener{
		
		public void onItemClickFromDialog(DialogFragment dialog, int whichItem);
		
	}
	
	// Use this instance of the interface to deliver action events
	Custom_Dialog_ListView_Listener mListener;

	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (Custom_Dialog_ListView_Listener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Setup Widget")
				.setItems(R.array.widget_category_list, new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
						
						// sends info back to the calling activity
						// which is the position of the click
						mListener.onItemClickFromDialog(Custom_Dialog_ListView.this, which);
					}
				});
		
		
		
		return builder.create();
	}


	
	

}
