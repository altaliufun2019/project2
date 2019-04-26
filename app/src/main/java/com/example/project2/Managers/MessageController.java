package com.example.project2.Managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.project2.MainActivity;

import java.net.InetAddress;
import java.util.List;

public class MessageController {
    private static final MessageController ourInstance = new MessageController();
    private static final int recentlyTime = 5 * 60;
    static final int POSTS = -1;

    public static MessageController getInstance() {
        return ourInstance;
    }

    private MessageController() {}

    public void getPosts() {
        if (isInternetAvailable() || isRecentlyUpdated(POSTS)) {
            StorageManager.INSTANCE.load_posts();
        } else {
            List<Post> posts = ConnectionManager.getInstance().getPosts();
            StorageManager.INSTANCE.save(posts);
        }
    }

    public void getComments(int id) {
        if (isInternetAvailable() || isRecentlyUpdated(id)){
            StorageManager.INSTANCE.load(id);
        }
        else{
            StorageManager.INSTANCE.save(ConnectionManager.getInstance().getComments(id), id);
        }
    }

    private boolean isInternetAvailable() {
        boolean status=false;
        try{
            ConnectivityManager cm = (ConnectivityManager) MainActivity.getContext().
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState()==NetworkInfo.State.CONNECTED) {
                status= true;
            }else {
                netInfo = cm.getNetworkInfo(1);
                if(netInfo!=null && netInfo.getState()==NetworkInfo.State.CONNECTED)
                    status= true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return status;
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
