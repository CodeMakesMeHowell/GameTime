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
import com.example.gametime.model.Venue;

import java.util.ArrayList;

public class EditVenueActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView activityNameTxt, description;
    private Button addActivityButton;
    private Venue editVenue;
    VenueActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_venue);

        recyclerView = findViewById(R.id.EV_recyclerView);
        activityNameTxt = findViewById(R.id.EV_activityNameTxt);
        addActivityButton = findViewById(R.id.EV_addActivityBtn);

        description = findViewById(R.id.EV_Description);
        description.setText("Current Activities for " + getIntent().getStringExtra("venue_name"));

        fetchVenue();
    }

    private void fetchVenue() {
        FirebaseConfig.getInstance().adminBehavior.getVenue(getIntent().getStringExtra("venue_name"), new GTFirebaseListener<Venue>() {
            @Override
            public void onComplete(Venue venue) {
                editVenue = venue;

                addActivityButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addActivity();
                    }
                });

                setAdapter();
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(EditVenueActivity.this, "Failed to get venue activities", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void setAdapter() {
        adapter = new VenueActivityAdapter(this, editVenue.getActivities(), false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void addActivity() {
        String activityName = activityNameTxt.getText().toString();

        if (editVenue.getActivities().contains(activityName)) {
            Toast.makeText(EditVenueActivity.this, "There is already an activity with this name!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            editVenue.getActivities().add(activityName);

            //firebase here
            FirebaseConfig.getInstance().adminBehavior.addVenue(editVenue, new GTFirebaseListener() {
                @Override
                public void onComplete(Object o) {

                    recyclerView.getAdapter().notifyDataSetChanged();
                }

                @Override
                public void onFailure(String message) {
                    Toast.makeText(EditVenueActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }, true);
        }
    }
}