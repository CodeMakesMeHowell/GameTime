package com.example.gametime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gametime.firebase.FirebaseDBPaths;
import com.example.gametime.model.Event;
import com.example.gametime.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


public class UpcomingEventsActivity extends AppCompatActivity {

    DatabaseReference database;
    private RecyclerView recyclerView;
    private EventAdapter.RecyclerViewClickListener listener;
    ArrayList<Event> list;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getIntent().getStringExtra("venue_name") != null)
            startActivity(new Intent(UpcomingEventsActivity.this, SelectVenueActivity.class));
        else
            startActivity(new Intent(UpcomingEventsActivity.this, SelectionActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_events);
        ((Button)findViewById(R.id.admin_schedule_event_button)).setVisibility(
                getIntent().getStringExtra("venue_name") != null ? View.VISIBLE : View.GONE);
        recyclerView = findViewById(R.id.upcomingevents);
        list = new ArrayList<>();

        setOnClickListener();
        EventAdapter adapter = new EventAdapter(this, list, listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance().getReference(FirebaseDBPaths.EVENTS.getPath());
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Event event = dataSnapshot.getValue(Event.class);
                    if(getIntent().getStringExtra("venue_name") == null ||
                            event.getVenue().equals(getIntent().getStringExtra("venue_name")))
                        list.add(event);
                }
                Collections.sort(list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if(User.currentUser.isAdmin())
        {
            ((TextView)findViewById(R.id.EV_Description)).setText("EVENTS");
            findViewById(R.id.EV_upcomingPrompt).setVisibility(View.GONE);
        }

        if(!User.currentUser.isAdmin() || getIntent().getStringExtra("prev_activity").equals("Selection")) {
            findViewById(R.id.admin_edit_venue_btn).setVisibility(View.GONE);
        }


    }

    public void onAdminScheduleEvent(View view){
        Intent i = new Intent(UpcomingEventsActivity.this, ScheduleEventActivity.class);
        i.putExtra("venue_name", getIntent().getStringExtra("venue_name"));
        i.putExtra("num_events", getIntent().getIntExtra("num_events", 0));
        i.putExtra("activities", getIntent().getStringArrayListExtra("activities"));
        startActivity(i);

    }

    public void onEditVenueBtnPressed(View view) {
        Intent i = new Intent(UpcomingEventsActivity.this, EditVenueActivity.class);
        i.putExtra("venue_name", getIntent().getStringExtra("venue_name"));
        startActivity(i);
    }

    private void setOnClickListener() {
        listener = new EventAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                if(getIntent().getStringExtra("venue_name") != null) return;
                Intent intent = new Intent(getApplicationContext(), EventActivity.class);
                Event event = list.get(position);
                intent.putExtra("event", event);
                startActivity(intent);
            }
        };
    }

}
