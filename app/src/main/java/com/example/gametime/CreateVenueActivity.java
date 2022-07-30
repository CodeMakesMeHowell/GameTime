package com.example.gametime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gametime.firebase.FirebaseConfig;
import com.example.gametime.firebase.GTFirebaseListener;
import com.example.gametime.model.Event;
import com.example.gametime.model.Venue;

import java.sql.Array;
import java.util.ArrayList;

public class CreateVenueActivity extends AppCompatActivity {
    private ArrayList<String> activityNames;
    private RecyclerView recyclerView;
    private TextView activityNameTxt, venueNameTxt;
    private Button addActivityButton, addVenueButton;
    VenueActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_venue);
        recyclerView = findViewById(R.id.activitiesRecyclerView);
        activityNames = new ArrayList<>();
        activityNameTxt = findViewById(R.id.activityNameInput);
        venueNameTxt = findViewById(R.id.venueNameInput);
        addActivityButton = findViewById(R.id.addActivityButton);
        addVenueButton = findViewById(R.id.createVenueBtn);

        activityNames.add("Sample Activity");

        addActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addActivity();
            }
        });
        addVenueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createVenue();
            }
        });

        setAdapter();
    }

    private void setAdapter() {
        adapter = new VenueActivityAdapter(this, activityNames);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void addActivity() {
        String activityName = activityNameTxt.getText().toString();

        if (activityNames.contains(activityName)) {
            Toast.makeText(CreateVenueActivity.this, "There is already an activity with this name!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            adapter.activities.add(activityName);
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    public void createVenue() {
        Venue venue = new Venue(venueNameTxt.getText().toString(), activityNames, new ArrayList<Event>());
        FirebaseConfig.getInstance().adminBehavior.addVenue(venue, new GTFirebaseListener() {
            @Override
            public void onComplete(Object o) {
                Toast.makeText(CreateVenueActivity.this, "Venue added", Toast.LENGTH_SHORT).show();
                finish(); //This should bring us back to the parent activity (Venue Selection)
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(CreateVenueActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}