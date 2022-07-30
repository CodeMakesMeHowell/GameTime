package com.example.gametime.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Date;

import java.util.ArrayList;

public class Event implements Comparable<Event>{
    String name;
    String activity;
    String start_time;
    String end_time;
    String venue;
    int num_players;

    public Event(){
        name = "";
        start_time = "";
        end_time = "";
        venue = "";
        num_players = -1;
        activity = "";
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", activity='" + activity + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", venue='" + venue + '\'' +
                ", num_players=" + num_players +
                '}';
    }

    public Event(String name, String start_time, String end_time, String venue, int num_players,
                 String activity){
        this.name = name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.venue = venue;
        this.num_players = num_players;
        this.activity = activity;
    }

    public int getNum_players(){
        return num_players;
    };
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


    @Override
    public boolean equals(Object other){
        if(other == null || !(other instanceof Event)) return false;
        Event otherEvent = (Event)other;
        return otherEvent.name.equals(name) && otherEvent.venue.equals(venue) &&
                otherEvent.start_time.equals(start_time) && otherEvent.end_time.equals(end_time);
    }

    @Override
    public int compareTo(Event other){
        return start_time.compareTo(other.getStart_time());
    }

    public String toUIDString() {
        return String.format("%s_%s_%s_%s", venue, name, start_time.toString(), end_time.toString());
    }
}
