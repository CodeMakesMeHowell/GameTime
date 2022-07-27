package com.example.gametime.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Venue {
    String name;
    ArrayList<String> activities;
    ArrayList<Event> events;
    private static final FirebaseDatabase database =
            FirebaseDatabase.getInstance("https://gametime-4360d-default-rtdb.firebaseio.com/");
    public Venue(){

    }
    public Venue(String name, ArrayList<String> activities, ArrayList<Event> events){
        this.name = name;
        this.activities = activities;
        this.events = events;
    }

    public void addToDb(){
        DatabaseReference myRef = database.getReference("Venues");
        myRef.child(name).setValue(this);
    }

    public String name(){
        return name;
    }

    public ArrayList<String> getActivities(){
        return activities;
    }
    public ArrayList<Event> getEvents(){
        return events;
    }
    @Override
    public boolean equals(Object other){
        if (other == null || !(other instanceof Venue)) return false;
        return ((Venue)other).name.equals(name);
    }
}
