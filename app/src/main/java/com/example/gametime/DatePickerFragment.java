package com.example.gametime;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    static int event_year;
    static int event_month;
    static int event_day;
    TextView textView;
    public DatePickerFragment(TextView textView){
        this.textView = textView;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        event_year = c.get(Calendar.YEAR);
        event_month = c.get(Calendar.MONTH);
        event_day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(requireContext(), this, event_year, event_month, event_day);
    }


    @Override
    public String toString(){
        String month_string = Integer.toString(event_month + 1);
        String day_string = Integer.toString(event_day);
        if(event_month < 10) month_string = "0" + month_string;
        if(event_day < 10 ) day_string = "0" + day_string;
        return event_year + "-" + month_string + "-" + day_string;
    }
    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        event_year = year;
        event_month = month;
        event_day = day;
        textView.setText(toString());

    }
}

