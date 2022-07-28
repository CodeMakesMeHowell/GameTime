package com.example.gametime.firebase;

import com.example.gametime.model.Event;
import com.example.gametime.model.User;
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

    private void getEventByID(String id){

    }

    @Override
    public void signUpForEvent(User user, Venue venue, Event event, GTFirebaseListener listener) {
        //TODO
    }
}
