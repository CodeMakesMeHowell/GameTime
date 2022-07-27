package com.example.gametime.firebase;

import com.example.gametime.model.Event;
import com.example.gametime.model.Venue;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Concrete implementation of all customer interactions
 * @author Nathan Wong
 */
class FirebaseCustomerStrategy extends FirebaseCustomerBehavior {

    public FirebaseCustomerStrategy(FirebaseDatabase db) {
        super(db);
    }

    @Override
    public ArrayList<Venue> getVenues() {
        return null; //TODO
    }

    @Override
    public ArrayList<Event> getEventsForVenue() {
        return null; //TODO
    }

    @Override
    public void scheduleEvent(Venue venue, Event event) throws GTFirebaseException {
        //TODO
    }

    @Override
    public void signUpForEvent(String username, Venue venue, Event event) throws GTFirebaseException {
        //TODO
    }
}
