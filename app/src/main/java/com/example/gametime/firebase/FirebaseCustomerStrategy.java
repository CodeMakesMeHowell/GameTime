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
        DatabaseReference ref = db.getReference(FirebaseDBPaths.EVENTS.getPath());
        ref.child(event.toUIDString()).setValue(event);
        ref = db.getReference(FirebaseDBPaths.VENUES.getPath());
        ref.child(venue_name).child("events").child(Integer.toString(num_events)).setValue(event);
    }

    private void getEventByID(String id){

    }

    @Override
    public void signUpForEvent(User user, Venue venue, Event event) throws GTFirebaseException {
        //TODO
    }
}
