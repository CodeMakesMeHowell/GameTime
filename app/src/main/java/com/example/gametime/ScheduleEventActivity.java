package com.example.gametime;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ScheduleEventActivity extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_event);
        Intent intent = getIntent();
        ArrayList<String> activities = intent.getStringArrayListExtra("activities");
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ScheduleEventActivity t = this;
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, activities);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);

        String eventName = ((EditText) findViewById(R.id.EventName)).getText().toString();

    }
}
