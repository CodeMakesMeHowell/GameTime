package com.example.gametime;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EventActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_info_activity);
        TextView eventTxt = findViewById(R.id.event_info);
        TextView startTxt = findViewById(R.id.start_info);

        String event = "N/A";
        String start = "??";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            event = extras.getString("event");
            start = extras.getString("start");
        }

        eventTxt.setText(event);
        startTxt.setText(start);
    }
}
