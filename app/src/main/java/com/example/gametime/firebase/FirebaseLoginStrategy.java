package com.example.gametime.firebase;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Concrete implementation of all login/user creation methods
 * @author Nathan Wong
 */
class FirebaseLoginStrategy extends FirebaseLoginBehavior {

    public FirebaseLoginStrategy(FirebaseDatabase db) {
        super(db);
    }

    @Override
    public boolean isValidPasswordForUser(String username, String password, GTFirebaseListener<Boolean> listener) {
        return false; //TODO
    }

    @Override
    public void createUser(String username, String password, GTFirebaseListener listener) {
        //TODO
    }
}
