package com.example.gametime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gametime.firebase.FirebaseDBPaths;
import com.example.gametime.model.Event;
import com.example.gametime.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ScheduledEventsActivity extends AppCompatActivity {

    ArrayList<Event> list;
    DatabaseReference joinedQuery;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduled_events);

        recyclerView = findViewById(R.id.scheduledevents);
        list = new ArrayList<>();

        JoinedAdapter adapter = new JoinedAdapter(this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (User.currentUser.getScheduled().contains("NO EVENTS")){
            Toast.makeText(ScheduledEventsActivity.this, "You currently have no events!", Toast.LENGTH_SHORT).show();
        }
        else{
            joinedQuery = FirebaseDatabase.getInstance().getReference(FirebaseDBPaths.EVENTS.getPath());
            for(String scheduledevent : User.currentUser.getScheduled()){
                joinedQuery.orderByKey().equalTo(scheduledevent).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                            Event event = dataSnapshot.getValue(Event.class);
                            list.add(event);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        }
    }
}