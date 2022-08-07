package com.example.gametime.model;

import android.content.Intent;
import android.widget.Toast;

import com.example.gametime.MainActivity;
import com.example.gametime.SignUpActivity;
import com.example.gametime.firebase.FirebaseConfig;
import com.example.gametime.firebase.FirebaseDBPaths;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class User {
    boolean admin;
    String name;
    String username;
    String password;
    ArrayList<String> events;   //Joined events
    ArrayList<String> scheduled; //Scheduled events

    public static User currentUser = null;

    public User(){
    }

    public User(String name, String username, String password){
        this.name = name;
        this.username = username;
        this.password = password;
        this.admin = true;
    }

    public void addEvent(String event){
        events.add(event);
    }

    public void addScheduled(String event){
        scheduled.add(event);
    }

    public User(boolean admin, String name, String username, String password, ArrayList<String> events, ArrayList<String> scheduled) {
        this.admin = admin;
        this.name = name;
        this.username = username;
        this.password = password;
        this.events = events;
        events.add("NO EVENTS");
        this.scheduled = scheduled;
        scheduled.add("NO EVENTS");
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<String> getEvents() {
        return events;
    }

    public ArrayList<String> getScheduled() {
        return scheduled;
    }

    public void addToDb(){
        DatabaseReference ref = FirebaseConfig.getInstance().getDbInstance().getReference();

        ref.child(FirebaseDBPaths.USERS.getPath()).child(username).child("name").setValue(this.name);
        ref.child(FirebaseDBPaths.USERS.getPath()).child(username).child("password").setValue(this.password);
        ref.child(FirebaseDBPaths.USERS.getPath()).child(username).child("events").setValue(this.events);
        ref.child(FirebaseDBPaths.USERS.getPath()).child(username).child("scheduled").setValue(this.scheduled);
        ref.child(FirebaseDBPaths.USERS.getPath()).child(username).child("admin").setValue(this.admin);
    }

    public static User userFromSnapshot(DataSnapshot userSnapshot) {
        return userSnapshot.getValue(User.class);
    }

    public void setUsername(String usrname){
        this.username = usrname;
    }

    @Override
    public String toString() {
        return "User{" +
                "admin=" + admin +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", events=" + events +
                '}';
    }
}
