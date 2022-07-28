package com.example.gametime.model;

import android.content.Intent;
import android.widget.Toast;

import com.example.gametime.MainActivity;
import com.example.gametime.SignUpActivity;
import com.example.gametime.firebase.FirebaseDBPaths;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class User {
    Boolean admin;
    String name;
    String username;
    String password;
    ArrayList<String> events;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://gametime-4360d-default-rtdb.firebaseio.com/");

    public User(){
    }

    public User(String name, String username, String password, boolean admin){
        this.name = name;
        this.username = username;
        this.password = password;
        this.events = new ArrayList<String>();
        events.add("NO EVENTS");
        this.admin = admin;
    }

    public String getName(){
        return name;
    }

    public void addToDb(){
        if(this.admin)
        {
            ref.child(FirebaseDBPaths.USERS.getPath()).child(FirebaseDBPaths.ADMINS.getPath()).child(username).child("name").setValue(this.name);
            ref.child(FirebaseDBPaths.USERS.getPath()).child(FirebaseDBPaths.ADMINS.getPath()).child(username).child("password").setValue(this.password);
            ref.child(FirebaseDBPaths.USERS.getPath()).child(FirebaseDBPaths.ADMINS.getPath()).child(username).child("events").setValue(this.events);
        } else {
            ref.child(FirebaseDBPaths.USERS.getPath()).child(FirebaseDBPaths.CUSTOMERS.getPath()).child(username).child("name").setValue(this.name);
            ref.child(FirebaseDBPaths.USERS.getPath()).child(FirebaseDBPaths.CUSTOMERS.getPath()).child(username).child("password").setValue(this.password);
            ref.child(FirebaseDBPaths.USERS.getPath()).child(FirebaseDBPaths.CUSTOMERS.getPath()).child(username).child("events").setValue(this.events);
        }
    }
}
