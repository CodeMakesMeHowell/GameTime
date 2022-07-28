package com.example.gametime.firebase;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.gametime.model.Event;
import com.example.gametime.model.User;
import com.example.gametime.model.Venue;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * Concrete implementation of all customer interactions
 * @author Nathan Wong
 */
public class FirebaseCustomerStrategy extends FirebaseCustomerBehavior {

    public FirebaseCustomerStrategy(FirebaseDatabase db) {
        super(db);
    }

    @Override
    public void listenForVenues(ValueEventListener v){
        DatabaseReference myRef = db.getReference("Venues");
        myRef.addValueEventListener(v);
    }
    
    @Override
    public ArrayList<Venue> getVenues(GTFirebaseListener<ArrayList<Venue>> listener) {
        return null; //TODO
    }

    @Override
    public ArrayList<Event> getEventsForVenue(Venue venue, GTFirebaseListener<ArrayList<Event>> listener) {
        return null; //TODO
    }

    @Override
    public void scheduleEvent(Venue venue, Event event, GTFirebaseListener listener) {
        //TODO
    }

    @Override
    public void signUpForEvent(User user, Venue venue, Event event, GTFirebaseListener listener) {
        //TODO
    }
}
