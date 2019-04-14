package com.example.project2.Managers;

public class ConnectionManager {
    private static final ConnectionManager ourInstance = new ConnectionManager();

    public static ConnectionManager getInstance() {
        return ourInstance;
    }

    private ConnectionManager() {
    }

}
