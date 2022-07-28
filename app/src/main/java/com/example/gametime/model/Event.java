package com.example.gametime.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Date;

import java.util.ArrayList;

public class Event implements Comparable<Event>{
    String name;
    String start_time;
    String end_time;
    String venue;
    int num_players;
    ArrayList<User> users;

    public Event(){
        name = "";
        start_time = "";
        end_time = "";
        venue = "";
        num_players = -1;
    }
    public Event(String name, String start_time, String end_time, String venue, int num_players){
        this.name = name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.venue = venue;
        this.num_players = num_players;
        this.users =new ArrayList<User>();
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

    public ArrayList<User> getUsers(){return users;}

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
}
