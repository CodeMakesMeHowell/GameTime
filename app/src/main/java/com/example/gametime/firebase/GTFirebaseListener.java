package com.example.gametime.firebase;

/**
 * Acts like a runnable for firebase calls
 * @param <T> the return type (or null if void) of the method called
 * @author Nathan Wong
 */
public interface GTFirebaseListener <T> {
    public void onComplete(T t);
    public void onFailure(String message);
}