package com.example.gametime.firebase;

public enum FirebaseDBPaths {
    VENUES("venues"),
    EVENTS("events"),
    USERS("users"),
    ADMINS(USERS.path + "/admin"),
    CUSTOMERS(USERS.path + "/customer");

    private final String path;

    private FirebaseDBPaths(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}