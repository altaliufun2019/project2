package com.example.project2.Managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.project2.MainActivity;
import com.example.project2.MessageCenter.DispatchQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConnectionManager {
    private static String postAddress = "https://jsonplaceholder.typicode.com/posts";
    private static String commentAddress = "https://jsonplaceholder.typicode.com/comments?postId=";
    private static final ConnectionManager ourInstance = new ConnectionManager();
    private RequestQueue requestQueue;

    public static ConnectionManager getInstance() {
        return ourInstance;
    }

    private ConnectionManager() {
        Cache cache = new DiskBasedCache(MainActivity.getContext().getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
    }

    public void getPosts() {
        final List<Post> posts = new ArrayList<>(100);
        requestQueue.add(new JsonArrayRequest(postAddress, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                final List<Post> posts = new ArrayList<>(100);
                System.out.println(response);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject json = response.getJSONObject(i);
                        posts.add(new Post(json.getInt("userId"), json.getInt("ID"),
                                json.getString("title"), json.getString("body")));

                    } catch (JSONException e) {
                        Log.e("json parsing", "post out of range");
                    }
                }
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        StorageManager.INSTANCE.save(posts);
                    }
                });

            }
        }, null));
        updateLastUpdate(MessageController.POSTS);
    }

    void getComments(final int id) {
        requestQueue.add(new JsonArrayRequest(commentAddress + String.valueOf(id),
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                final List<Comment> comments = new ArrayList<>(5);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject json = response.getJSONObject(i);
                        comments.add(new Comment(json.getInt("postId"), json.getInt("ID"),
                                json.getString("name"), json.getString("email"),
                                json.getString("body")));
                    }
                    catch (JSONException ignored){
                        Log.e("json parsing", "comment out of range");
                    }
                }
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        StorageManager.INSTANCE.save(comments, id);
                    }
                });
            }
        }, null));
        updateLastUpdate(id);
    }

    private void updateLastUpdate(int postId) {
        Context context = MainActivity.getContext();
        SharedPreferences preferences = context.getSharedPreferences("LastCheck", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(String.valueOf(postId), System.currentTimeMillis());
        editor.apply();
    }

}
