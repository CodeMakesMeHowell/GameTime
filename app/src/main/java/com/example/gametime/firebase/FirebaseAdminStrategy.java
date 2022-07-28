package com.example.gametime.firebase;

import androidx.annotation.NonNull;

import com.example.gametime.model.Event;
import com.example.gametime.model.User;
import com.example.gametime.model.Venue;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
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
    public void addVenue(Venue venue, GTFirebaseListener listener) {
        //TODO
    }

    @Override
    public void removeVenue(Venue venue, GTFirebaseListener listener) {
        //TODO
    }

    @Override
    public void removeEvent(Venue venue, Event event, GTFirebaseListener listener) {
        db.getReference(FirebaseDBPaths.EVENTS.getPath()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild(event.toUIDString())) {
                    listener.onFailure("event do not exist");
                } else {
                    //db.getReference(FirebaseDBPaths.USERS.getPath()).child(;//TODO
                    db.getReference(FirebaseDBPaths.VENUES.getPath()).child(venue.toUIDString()).child(event.toUIDString()).removeValue();
                    db.getReference(FirebaseDBPaths.EVENTS.getPath()).child(event.toUIDString()).removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onFailure("Something went wrong with the database");
                System.err.println(databaseError.getMessage());
            }
        });
        /*
        On an event deletion we will have to:
            - delete the event id from signed up users
            - delete the event from its associated venue
            - delete the event itself
         */
    }

    @Override
    public ArrayList<Venue> getVenues(GTFirebaseListener<ArrayList<Venue>> listener) {
        return null;
    }

    @Override
    public ArrayList<Event> getEventsForVenue(Venue venue, GTFirebaseListener<ArrayList<Event>> listener) {
        db.getReference(FirebaseDBPaths.VENUES.getPath()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {//TODO

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onFailure("Something went wrong with the database");
                System.err.println(databaseError.getMessage());
            }
        });
        return null;
    }
}
