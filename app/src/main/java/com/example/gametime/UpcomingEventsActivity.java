package com.example.gametime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gametime.firebase.FirebaseDBPaths;
import com.example.gametime.model.Event;
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
        startActivity(new Intent(UpcomingEventsActivity.this, SelectionActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_events);

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
                    list.add(event);
                }
                Collections.sort(list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setOnClickListener() {
        listener = new EventAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), EventActivity.class);
                Event event = list.get(position);
                intent.putExtra("event", event);
                startActivity(intent);
            }
        };
    }

}
