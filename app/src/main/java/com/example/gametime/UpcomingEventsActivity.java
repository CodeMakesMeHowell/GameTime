package com.example.gametime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


public class UpcomingEventsActivity extends AppCompatActivity {

    String[] up_events, dates;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_events);

        recyclerView = findViewById(R.id.upcomingevents);

        up_events = getResources().getStringArray(R.array.upcoming_events);
        dates = getResources().getStringArray(R.array.date);

        EventAdapter adapter = new EventAdapter(this, up_events, dates);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

}
