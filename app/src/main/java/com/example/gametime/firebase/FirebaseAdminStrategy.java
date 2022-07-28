package com.example.gametime.firebase;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.gametime.model.Event;
import com.example.gametime.model.User;
import com.example.gametime.model.Venue;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
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
        db.getReference(FirebaseDBPaths.VENUES.getPath()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(venue.toUIDString())) {
                    listener.onFailure("A venue with the same name already exists!");
                } else {
                    db.getReference(FirebaseDBPaths.VENUES.getPath()).child(venue.toUIDString()).setValue(venue).addOnCompleteListener(task -> {
                        listener.onComplete(null);
                    }).addOnFailureListener(task -> {
                        listener.onFailure("Something went wrong with the database");
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onFailure("Something went wrong with the database");
                System.err.println(databaseError.getMessage());
            }
        });
    }

    @Override
    public void removeVenue(Venue venue, GTFirebaseListener listener) {
        //TODO
        /*
        On a venue deletion we will have to:
            - delete all associated events [removeEvent()]
            - delete the venue itself
         */
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
