package com.example.gametime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        TextView displayName = (TextView) findViewById(R.id.nameOfUser);
        displayName.setText(MainActivity.nameTxt);

        Button venueBtn = findViewById(R.id.venueButton);
        Button upcomingBtn = findViewById(R.id.upcomingEventsButton);
        Button joinedBtn = findViewById(R.id.joinedEventsButton);

        TextView logOut = (TextView) findViewById(R.id.LogOutTxt);

        venueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectionActivity.this, SelectVenueActivity.class);
                startActivity(intent);
            }
        });

        upcomingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectionActivity.this, UpcomingEventsActivity.class);
                startActivity(intent);
            }
        });

        joinedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectionActivity.this, JoinedEventsActivity.class);
                startActivity(intent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSignIn = new Intent(SelectionActivity.this, MainActivity.class);
                startActivity(intentSignIn);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intentSignIn = new Intent(SelectionActivity.this, MainActivity.class);
        startActivity(intentSignIn);
        finish();
    }

}