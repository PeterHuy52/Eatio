package com.doanchuyennganh.eatio.views.ui;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by TungHo on 05/09/2017.
 */

public abstract class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    int hour = 8;
    int minute = 0;

    public void setHour(int h){
        hour = h;
    }

    public void setMinute(int m){
        minute = m;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }


}
