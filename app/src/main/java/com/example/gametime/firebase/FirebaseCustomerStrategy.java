package com.example.gametime.firebase;

import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.gametime.EventActivity;
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
        DatabaseReference myRef = db.getReference(FirebaseDBPaths.VENUES.getPath());
        myRef.addValueEventListener(v);
    }
    @Override
    public ArrayList<Venue> getVenues() {
        return null;
    }

    @Override
    public ArrayList<Event> getEventsForVenue(Venue venue) {
        return null; //TODO
    }

    @Override
    public void scheduleEvent(String venue_name, Event event, int num_events) throws GTFirebaseException {
        //sign up the user when they schedule
        signUp(event);
        DatabaseReference ref = db.getReference(FirebaseDBPaths.EVENTS.getPath());
        ref.child(event.toUIDString()).setValue(event);
        ref = db.getReference(FirebaseDBPaths.VENUES.getPath());
        ref.child(venue_name).child("events").child(Integer.toString(num_events)).setValue(event);

    }

    @Override
    public void listenForEvents(ValueEventListener v){
        DatabaseReference ref = db.getReference(FirebaseDBPaths.EVENTS.getPath());
        ref.addValueEventListener(v);
    }


    @Override
    public void signUp(Event event){
        event.register();
        User curr_user = User.currentUser;
        DatabaseReference ref = db.getReference(FirebaseDBPaths.USERS.getPath());
        int num = curr_user.getEvents().size();
        ref.child(curr_user.getUsername()).child("events").child(String.valueOf(num)).setValue(event.toUIDString());
        curr_user.addEvent(event.toUIDString());
    }

}
