package com.example.project2.Managers;

import android.os.Handler;
import android.os.Looper;

import com.example.project2.MessageCenter.DispatchQueue;

import org.json.JSONObject;

public class ConnectionManager {
    private static final ConnectionManager ourInstance = new ConnectionManager();
    Handler mainHandler = new Handler(Looper.getMainLooper());

    public static ConnectionManager getInstance() {
        return ourInstance;
    }

    void onResponseReceive() {

    }


    private ConnectionManager() {
    }


    public void getPosts() {
        DispatchQueue myQueue = new DispatchQueue("getPosts");
        //myQueue.post();
    }
}
