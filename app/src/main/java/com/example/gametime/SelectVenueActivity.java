package com.example.gametime;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gametime.firebase.FirebaseConfig;
import com.example.gametime.firebase.FirebaseCustomerBehavior;
import com.example.gametime.firebase.FirebaseCustomerStrategy;
import com.example.gametime.model.User;
import com.example.gametime.model.Venue;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectVenueActivity extends AppCompatActivity implements VenueAdapter.ItemClickListener {
    VenueAdapter adapter;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SelectVenueActivity.this, SelectionActivity.class));
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_list);

        TextView prompt = (TextView) findViewById(R.id.promptUserVenues);

        if(User.currentUser.isAdmin()) {
            prompt.setText("Click on any venue to see it's events");
        }

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
                LinearLayoutManager layoutManager = new LinearLayoutManager(t);
                recyclerView.setLayoutManager(layoutManager);
                adapter = new VenueAdapter(t, venues);
                adapter.setClickListener(t);
                recyclerView.setAdapter(adapter);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                        layoutManager.getOrientation());
                recyclerView.addItemDecoration(dividerItemDecoration);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("db", "Failed to read value.", databaseError.toException());
            }
        };
        FirebaseConfig.getInstance().customerBehavior.listenForVenues(val);

        Button addVenueBtn = (Button) findViewById(R.id.addVenueBtn);
        addVenueBtn.setVisibility(User.currentUser.isAdmin() ? View.VISIBLE : View.GONE);
    }
    @Override
    public void onItemClick(View view, int position) {
        int num_events = 0;
        if(adapter.getItem(position).getEvents() != null)
            num_events = adapter.getItem(position).getEvents().toArray().length;
        Intent intent;
        if(!User.currentUser.isAdmin())
            intent = new Intent(SelectVenueActivity.this, ScheduleEventActivity.class);
        else
            intent = new Intent(SelectVenueActivity.this, UpcomingEventsActivity.class);
            intent.putExtra("venue_name", adapter.getItem(position).getName());
            intent.putExtra("num_events", num_events);
            intent.putExtra("activities", adapter.getItem(position).getActivities());
            intent.putExtra("prev_activity", "Venues");
            startActivity(intent);

    }
    public void onAddVenueBtnClick(View view) {
        Intent intent = new Intent(this, CreateVenueActivity.class);
        startActivity(intent);
    }
}
