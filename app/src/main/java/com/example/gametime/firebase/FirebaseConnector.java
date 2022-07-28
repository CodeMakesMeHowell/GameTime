package com.example.gametime.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Classes that interact with the firebase db should extend this class
 * @author Nathan Wong
 */
public abstract class FirebaseConnector {
    protected FirebaseDatabase db;

    public FirebaseConnector(FirebaseDatabase db) {
        this.db = db;
    }

    public DatabaseReference getReference(String path){
        return db.getReference(path);
    }

}
