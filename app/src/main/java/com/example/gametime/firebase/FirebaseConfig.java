package com.example.gametime.firebase;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Singleton class to centralize all firebase configuration
 * @author Nathan Wong
 */
public final class FirebaseConfig {
    private static FirebaseConfig config;

    private final String FIREBASE_URL = "https://gametime-4360d-default-rtdb.firebaseio.com/";
    private final FirebaseDatabase dbInstance = FirebaseDatabase.getInstance(FIREBASE_URL);

    private final FirebaseLoginBehavior loginBehvior = new FirebaseLoginStrategy(dbInstance);
    private final FirebaseAdminBehavior adminBehavior = new FirebaseAdminStrategy(dbInstance);
    private final FirebaseCustomerBehavior customerBehavior = new FirebaseCustomerStrategy(dbInstance);

    private FirebaseConfig() {

    }

    public static FirebaseConfig getInstance() {
        if (config == null) {
            config = new FirebaseConfig();
        }
        return config;
    }
}
