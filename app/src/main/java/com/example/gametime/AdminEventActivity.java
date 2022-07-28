package com.example.gametime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//1.Admin can choose/type in an existed venue in admins' main UI and then jump into this page
//2.this page contains venue name on the top and the upcoming events name & time(or 'no events
// in this venue') and go back button
//3.Admin can type in an existed event name and delete(or modify) an event

public class AdminEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_event);
    }
}