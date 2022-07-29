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

import java.util.ArrayList;

public class CreateVenueActivity extends AppCompatActivity {
    private ArrayList<String> activityNames;
    private RecyclerView recyclerView;
    private TextView activityNameTxt;
    private Button addActivityButton, addVenueButton;
    VenueActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_venue);
        recyclerView = findViewById(R.id.activitiesRecyclerView);
        activityNames = new ArrayList<>();
        activityNameTxt = findViewById(R.id.activityNameInput);
        addActivityButton = findViewById(R.id.addActivityButton);
        addVenueButton = findViewById(R.id.addVenueBtn);

        activityNames.add("What the fuck is goin on");

        addActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addActivity();
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
        System.out.println(activityNameTxt);
        String activityName = activityNameTxt.getText().toString();

        if(activityNames.contains(activityName)){
            Toast.makeText(CreateVenueActivity.this, "There is already an activity with this name!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            adapter.activities.add(activityName);
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }
}