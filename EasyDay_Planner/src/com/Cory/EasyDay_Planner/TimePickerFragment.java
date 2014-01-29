package com.Cory.EasyDay_Planner;

import android.app.Activity;
import android.support.v4.app.DialogFragment;

public class TimePickerFragment extends DialogFragment{
	
	
	public interface OnTimeSetListener {

	}

	// The activity that creates the instance of this dialog
	// must implement these methods in order to receive 
	// the information coming back
	public interface TimePickerDialog {
		
		public void onTimeSet(TimePicker view, int hourOfDay, int minute);

	}
	
	TimePickerDialog mListener;
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (TimePickerDialog) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }


}
