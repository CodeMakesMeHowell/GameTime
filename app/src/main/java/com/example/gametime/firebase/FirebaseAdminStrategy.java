package com.example.gametime.firebase;

import androidx.annotation.NonNull;

import com.example.gametime.model.Event;
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
    public void addVenue(Venue venue, GTFirebaseListener listener, boolean overwrite) {
        if(venue == null) {
            listener.onFailure("Failed to get valid venue object (nullReference)");
            return;
        } else if (venue.getName().length() == 0) {
            listener.onFailure("Failed: Venue name cannot be empty!");
            return;
        }

        db.getReference(FirebaseDBPaths.VENUES.getPath()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!overwrite && dataSnapshot.hasChild(venue.toUIDString())) {
                    listener.onFailure("Failed: A venue with the same name already exists!");
                } else {
                    dataSnapshot.child(venue.toUIDString()).getRef().setValue(venue).addOnCompleteListener((task) -> {
                        listener.onComplete(null);
                    }).addOnFailureListener((task) -> {
                        listener.onFailure("Failed: something went wrong with the database :(");
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onFailure("Failed: something went wrong with the database :(");
            }
        });
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
    public void getVenue(String venueName, GTFirebaseListener<Venue> listener) {
        db.getReference(FirebaseDBPaths.VENUES.getPath()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listener.onComplete(dataSnapshot.child(venueName).getValue(Venue.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onFailure("Something went wrong with the database");
                System.err.println(databaseError.getMessage());
            }
        });
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
