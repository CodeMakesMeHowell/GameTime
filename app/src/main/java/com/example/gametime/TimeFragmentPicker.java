package com.example.gametime;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimeFragmentPicker extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    TextView display;
    int hour;
    int minute;
    public TimeFragmentPicker(TextView display){
        this.display = display;
    }

    @Override
    public String toString(){
        String hour_string = Integer.toString(hour);
        String minute_string = Integer.toString(minute);
        if(hour < 10) hour_string = "0" + hour_string;
        if(minute < 10) minute_string = "0" + minute_string;
        return hour_string + ":" + minute_string;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        this.hour = hourOfDay;
        this.minute = minute;
        display.setText(toString());
    }
}
