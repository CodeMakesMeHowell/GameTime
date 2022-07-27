package com.example.gametime.firebase;

/**
 * Thrown when an invalid request is detected
 * @author Nathan Wong
 */
public class GTFirebaseException extends Exception {
    private String message;

    public GTFirebaseException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
