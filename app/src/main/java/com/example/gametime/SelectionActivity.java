package com.example.gametime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gametime.model.User;

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        TextView displayName = findViewById(R.id.nameOfUser);
        displayName.setText(MainActivity.nameTxt);

        Button venueBtn = findViewById(R.id.venueButton);
        Button upcomingBtn = findViewById(R.id.upcomingEventsButton);
        Button joinedBtn = findViewById(R.id.joinedEventsButton);
        Button scheduledBtn = findViewById(R.id.scheduledEventsButton);

        TextView logOut = findViewById(R.id.LogOutTxt);

        venueBtn.setOnClickListener(view -> {
            Intent intent = new Intent(SelectionActivity.this, SelectVenueActivity.class);
            startActivity(intent);
        });

        upcomingBtn.setOnClickListener(view -> {
            Intent intent = new Intent(SelectionActivity.this, UpcomingEventsActivity.class);
            intent.putExtra("prev_activity", "Selection");
            startActivity(intent);
        });

        joinedBtn.setOnClickListener(view -> {
            Intent intent = new Intent(SelectionActivity.this, JoinedEventsActivity.class);
            startActivity(intent);
        });

        scheduledBtn.setOnClickListener(view -> {
            Intent intent = new Intent(SelectionActivity.this, ScheduledEventsActivity.class);
            startActivity(intent);
        });

        logOut.setOnClickListener(view -> {
            Intent intentSignIn = new Intent(SelectionActivity.this, MainActivity.class);
            startActivity(intentSignIn);
            Toast.makeText(SelectionActivity.this, "Successfully Logged Out", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        Intent intentSignIn = new Intent(SelectionActivity.this, MainActivity.class);
        startActivity(intentSignIn);
        Toast.makeText(SelectionActivity.this, "Successfully Logged Out", Toast.LENGTH_SHORT).show();
        finish();
    }

}