package com.example.project2.UIClasses;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.project2.Constants.Constants;
import com.example.project2.Managers.Comment;
import com.example.project2.Managers.MessageController;
import com.example.project2.Managers.Post;
import com.example.project2.MessageCenter.NotificationCenter;
import com.example.project2.R;
import com.example.project_1.UIComponents.DataAdapter.CommentAdapter;
import com.example.project_1.UIComponents.DataAdapter.DataNumberAdapter;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {
    private static Context applicationContext = null;
    private CommentAdapter mAdapter;
    private RecyclerView rvData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationContext = getApplicationContext();

        setContentView(R.layout.activity_main);

        initializeRecyclerView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // initializing and creating recycler view
    private void initializeRecyclerView(){
        rvData = findViewById(R.id.numList);
        CommentAdapter adapter = new CommentAdapter(MessageController.getInstance().comments);
        mAdapter = adapter;
        rvData.setAdapter(adapter);
        rvData.setLayoutManager(new LinearLayoutManager(this));
    }

}
