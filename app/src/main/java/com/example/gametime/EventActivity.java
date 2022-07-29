package com.example.gametime;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EventActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_info_activity);
        TextView eventTxt = findViewById(R.id.eventinfo);
        TextView startTxt = findViewById(R.id.startinfo);
        TextView endTxt = findViewById(R.id.endinfo);
        TextView venueTxt = findViewById(R.id.venueinfo);

        String event = "";
        String start = "";
        String end = "";
        String venue = "";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            event = extras.getString("name");
            start = extras.getString("start_time");
            end = extras.getString("end_time");
            venue = extras.getString("venue");
        }

        eventTxt.setText(event);
        startTxt.setText(start);
        endTxt.setText(end);
        venueTxt.setText(venue);
    }
}
