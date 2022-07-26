package com.example.gametime;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class Event {
    String name;
    String start_time;
    String end_time;
    String venue;
    private static final FirebaseDatabase database =
            FirebaseDatabase.getInstance("https://gametime-4360d-default-rtdb.firebaseio.com/");
    public Event(){

    }
    public Event(String name, String start_time, String end_time, String venue){
        this.name = name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.venue = venue;
    }

    public String getName(){
        return name;
    }

    public String getStart_time(){
        return start_time;
    }

    public String getEnd_time(){
        return end_time;
    }

    public String getVenue(){
        return venue;
    }
    /**
     * Adds the event to the database
     */
    public void addToDb(){
        DatabaseReference myRef = database.getReference("Events");
        myRef.child(name).setValue(this);
    }

    @Override
    public boolean equals(Object other){
        if(other == null || !(other instanceof Event)) return false;
        Event otherEvent = (Event)other;
        return otherEvent.name.equals(name) && otherEvent.venue.equals(venue) &&
                otherEvent.start_time.equals(start_time) && otherEvent.end_time.equals(end_time);
    }
}
