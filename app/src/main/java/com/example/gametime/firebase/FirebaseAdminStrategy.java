package com.example.gametime.firebase;

import com.example.gametime.model.Event;
import com.example.gametime.model.User;
import com.example.gametime.model.Venue;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Concrete implementations of admin database interactions
 *
 * @author Nathan Wong
 */
class FirebaseAdminStrategy extends FirebaseAdminBehavior {
    public FirebaseAdminStrategy(FirebaseDatabase db) {
        super(db);
    }

    @Override
    public void addVenue(Venue venue) throws GTFirebaseException {
        //TODO
    }

    @Override
    public void removeVenue(Venue venue) {
        //TODO
    }

    @Override
    public void removeEvent(Venue venue, Event event) {
        //TODO
    }

    @Override
    public ArrayList<Venue> getVenues() {
        return null; //TODO
    }

    @Override
    public void listenForVenues(ValueEventListener val) {

    }

    @Override
    public ArrayList<Event> getEventsForVenue(Venue venue) {
        return null; //TODO
    }

    @Override
    public void scheduleEvent(Venue venue, Event event) throws GTFirebaseException {
        //TODO
    }

    @Override
    public void signUpForEvent(User user, Venue venue, Event event) throws GTFirebaseException {

    }

}
