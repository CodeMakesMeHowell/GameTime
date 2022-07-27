package com.example.gametime.firebase;

import com.example.gametime.model.Event;
import com.example.gametime.model.User;
import com.example.gametime.model.Venue;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Specifies all methods needed for customer interaction
 * @author Nathan Wong
 */
public abstract class FirebaseCustomerBehavior extends FirebaseConnector{
    public FirebaseCustomerBehavior(FirebaseDatabase db) {
        super(db);
    }

    public abstract ArrayList<Venue> getVenues();

    public abstract ArrayList<Event> getEventsForVenue(Venue venue);

    public abstract void scheduleEvent(Venue venue, Event event) throws GTFirebaseException;

    public abstract void signUpForEvent(User user, Venue venue, Event event) throws GTFirebaseException;
}
