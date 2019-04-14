package com.example.project2.Managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;

import com.example.project2.MainActivity;

import java.net.InetAddress;
import java.sql.Time;

import static android.support.v4.content.ContextCompat.getSystemService;

public class MessageController {
    private static final MessageController ourInstance = new MessageController();
    private static int recentlyTime = 5 * 60;

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

    private void updateLastUpdate(int postId) {
        Context context = MainActivity.getContext();
        SharedPreferences preferences = context.getSharedPreferences("LastCheck", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(String.valueOf(postId), System.currentTimeMillis());
        editor.apply();
    }

    private int timeFromLastUpdate(int postId) {
        Context context = MainActivity.getContext();
        SharedPreferences preferences = context.getSharedPreferences("LastChek", Context.MODE_PRIVATE);
        long lastCheck = 0;
        try {
            lastCheck = preferences.getLong(String.valueOf(postId), 0);
        } catch (Exception ignored) {
            return 0;
        }
        return (int) (System.nanoTime() - lastCheck) / (1000);
    }

    private Boolean isRecentlyUpdated(int postId) {
        return timeFromLastUpdate(postId) <= recentlyTime;
    }


}
