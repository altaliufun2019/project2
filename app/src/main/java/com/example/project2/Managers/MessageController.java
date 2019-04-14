package com.example.project2.Managers;

import android.content.Context;
import android.net.ConnectivityManager;

import java.net.InetAddress;
import java.sql.Time;

import static android.support.v4.content.ContextCompat.getSystemService;

public class MessageController {
    private static final MessageController ourInstance = new MessageController();

    public static MessageController getInstance() {
        return ourInstance;
    }

    private MessageController() {
    }


    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }

    private void updateLastUpdate(Time time){
        
    }


}
