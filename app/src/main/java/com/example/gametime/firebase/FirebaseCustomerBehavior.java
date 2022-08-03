package com.example.gametime.firebase;

import com.example.gametime.model.Event;
import com.example.gametime.model.User;
import com.example.gametime.model.Venue;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.EventListener;

/**
 * Specifies all methods needed for customer interaction
 * @author Nathan Wong
 */
public abstract class FirebaseCustomerBehavior extends FirebaseConnector{
    public FirebaseCustomerBehavior(FirebaseDatabase db) {
        super(db);
    }

    public abstract ArrayList<Venue> getVenues();

    public abstract void listenForVenues(ValueEventListener val);

    public abstract ArrayList<Event> getEventsForVenue(Venue venue);

    public abstract void scheduleEvent(String venue, Event event, int num_events) throws GTFirebaseException;

    public abstract void listenForEvents(ValueEventListener v);

    public abstract void signUp(Event event);
}
