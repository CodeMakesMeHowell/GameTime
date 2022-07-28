package com.example.gametime;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gametime.firebase.FirebaseConfig;
import com.example.gametime.firebase.FirebaseCustomerBehavior;
import com.example.gametime.firebase.FirebaseCustomerStrategy;
import com.example.gametime.model.Venue;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectVenueActivity extends AppCompatActivity implements VenueAdapter.ItemClickListener {
    VenueAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_list);
        SelectVenueActivity t = this;
        ValueEventListener val = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Venue> venues = new ArrayList<Venue>();
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Venue v = child.getValue(Venue.class);
                    venues.add(v);
                }
                RecyclerView recyclerView = findViewById(R.id.venue_recycler);
                recyclerView.setLayoutManager(new LinearLayoutManager(t));
                adapter = new VenueAdapter(t, venues);
                adapter.setClickListener(t);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("db", "Failed to read value.", databaseError.toException());
            }
        };
        FirebaseConfig.getInstance().customerBehavior.listenForVenues(val);
    }
    @Override
    public void onItemClick(View view, int position) {
        Intent i = new Intent(SelectVenueActivity.this, ScheduleEventActivity.class);
        i.putExtra("venue_name", adapter.getItem(position).getName());
        i.putExtra("activities", adapter.getItem(position).getActivities());
        startActivity(i);
    }
}
