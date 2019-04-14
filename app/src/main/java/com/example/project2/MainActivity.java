package com.example.project2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


@SuppressLint("Registered")
public class MainActivity extends AppCompatActivity {
    private static Context applicationContext = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationContext = getApplicationContext();
    }

    public static Context getContext(){
        return applicationContext;
    }
}

