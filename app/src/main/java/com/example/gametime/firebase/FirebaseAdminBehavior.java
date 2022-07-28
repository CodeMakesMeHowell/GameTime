package com.example.gametime.firebase;

import com.example.gametime.model.Event;
import com.example.gametime.model.Venue;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Specifies all methods needed for admin interaction
 * @author Nathan Wong
 */
public abstract class FirebaseAdminBehavior extends FirebaseConnector {
    public FirebaseAdminBehavior(FirebaseDatabase db) {
        super(db);
    }

    public abstract void addVenue(Venue venue, GTFirebaseListener listener);
    public abstract void removeVenue(Venue venue, GTFirebaseListener listener);
    public abstract void removeEvent(Venue venue, Event event, GTFirebaseListener listener);
    public abstract ArrayList<Venue> getVenues(GTFirebaseListener<ArrayList<Venue>> listener);
    public abstract ArrayList<Event> getEventsForVenue(Venue venue, GTFirebaseListener<ArrayList<Event>> listener);
}
