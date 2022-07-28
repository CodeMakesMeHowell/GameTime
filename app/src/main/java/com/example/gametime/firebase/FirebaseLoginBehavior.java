package com.example.gametime.firebase;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Specifies all methods needed for login/user creation
 * @author Nathan Wong
 */
public abstract class FirebaseLoginBehavior extends FirebaseConnector {
    public FirebaseLoginBehavior(FirebaseDatabase db) {
        super(db);
    }

    public abstract boolean isValidPasswordForUser(String username, String password, GTFirebaseListener<Boolean> listener); //TODO

    public abstract void createUser(String username, String password, GTFirebaseListener listener); //TODO
}
