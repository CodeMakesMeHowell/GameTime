package com.example.gametime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gametime.firebase.FirebaseConfig;
import com.example.gametime.firebase.FirebaseCustomerStrategy;
import com.example.gametime.firebase.FirebaseDBPaths;
import com.example.gametime.model.Event;
import com.example.gametime.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EventActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_info_activity);
        TextView eventTxt = findViewById(R.id.eventinfo);
        TextView startTxt = findViewById(R.id.startinfo);
        TextView endTxt = findViewById(R.id.endinfo);
        TextView venueTxt = findViewById(R.id.venueinfo);
        TextView remainingTxt = findViewById(R.id.remaininginfo);

        Button registerbtn = findViewById(R.id.registerBtn);
        Event event = getIntent().getParcelableExtra("event");

        eventTxt.setText(event.getName());
        startTxt.setText(event.getStart_time());
        endTxt.setText(event.getEnd_time());
        venueTxt.setText(event.getVenue());
        remainingTxt.setText(Integer.toString(event.getNum_players()));

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(event.getNum_players() == 0){
                    Toast.makeText(EventActivity.this, "This event has reached its max capacity!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (User.currentUser.getEvents().contains(event.toUIDString())) {
                        Toast.makeText(EventActivity.this, "You have already registered for this event!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        FirebaseDatabase db = FirebaseDatabase.getInstance();
                        db.getReference(FirebaseDBPaths.USERS.getPath()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (User.currentUser.getEvents().contains("NO EVENTS")) {
                                    dataSnapshot.child(User.currentUser.getUsername()).child("events").child("0").getRef().setValue(event.toUIDString());
                                    User.currentUser.getEvents().remove("NO EVENTS");
                                } else {
                                    dataSnapshot.child(User.currentUser.getUsername()).child("events").child(Integer.toString(User.currentUser.getEvents().size())).getRef().setValue(event.toUIDString());

                                }
                                event.register();
                                User.currentUser.addEvent(event.toUIDString());
                                DatabaseReference ref = db.getReference(FirebaseDBPaths.EVENTS.getPath());
                                ref.child(event.toUIDString()).setValue(event);
                                startActivity(new Intent(EventActivity.this, UpcomingEventsActivity.class));
                                finish();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        Toast.makeText(EventActivity.this, "Successfully registered!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}