package com.example.project2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.project2.Constants.Constants;
import com.example.project2.MessageCenter.NotificationCenter;


@SuppressLint("Registered")
public class MainActivity extends AppCompatActivity implements NotificationCenter.NotificationTarget {
    private static Context applicationContext = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationContext = getApplicationContext();

        NotificationCenter.INSTANCE.register(this, Constants.Tasks.FETCH_DATA);

        setContentView(R.layout.activity_main);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NotificationCenter.INSTANCE.unregister(this, Constants.Tasks.FETCH_DATA);
    }

    @Override
    public void notified(int taskID) {

        switch (taskID) {
            case Constants.Tasks.FETCH_DATA:
                //TODO call set data
                break;
        }
    }

    public static Context getContext(){
        return applicationContext;
    }
}

