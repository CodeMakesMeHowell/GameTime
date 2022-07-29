package com.example.gametime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class UpcomingEventsActivity extends AppCompatActivity {

    String[] up_events, dates;
    private RecyclerView recyclerView;
    private EventAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_events);

        recyclerView = findViewById(R.id.upcomingevents);

        up_events = getResources().getStringArray(R.array.upcoming_events);
        dates = getResources().getStringArray(R.array.date);

        setOnClickListener();
        EventAdapter adapter = new EventAdapter(this, up_events, dates, listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setOnClickListener() {
        listener = new EventAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), EventActivity.class);
                intent.putExtra("event", up_events[position]);
                intent.putExtra("start", dates[position]);
                startActivity(intent);
            }
        };
    }

}
