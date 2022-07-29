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
    ArrayList<String> events;

    public static User currentUser = null;

    public User(){
    }

    public User(String name, String username, String password, boolean admin){
        this.name = name;
        this.username = username;
        this.password = password;
        admin = true;
    }

    public User(boolean admin, String name, String username, String password, ArrayList<String> events) {
        this.admin = admin;
        this.name = name;
        this.username = username;
        this.password = password;
        this.events = events;
        events.add("NO EVENTS");
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

    public void addToDb(){
        DatabaseReference ref = FirebaseConfig.getInstance().getDbInstance().getReference();

        ref.child(FirebaseDBPaths.USERS.getPath()).child(username).child("name").setValue(this.name);
        ref.child(FirebaseDBPaths.USERS.getPath()).child(username).child("password").setValue(this.password);
        ref.child(FirebaseDBPaths.USERS.getPath()).child(username).child("events").setValue(this.events);
        ref.child(FirebaseDBPaths.USERS.getPath()).child(username).child("admin").setValue(this.admin);
    }

    public static User userFromSnapshot(DataSnapshot userSnapshot) {
        return userSnapshot.getValue(User.class);
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
