package com.example.project2.UIClasses;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project2.Constants.Constants;
import com.example.project2.MainActivity;
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
    private MainActivity.ConnectionMonitor monitor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationContext = getApplicationContext();

        setContentView(R.layout.activity_comment);
        // starting toolbar
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        this.monitor = new MainActivity.ConnectionMonitor(this, (ImageView) findViewById(R.id.ivtest), (TextView) findViewById(R.id.connecting));

        initializeRecyclerView();

        ((TextView)findViewById(R.id.comment_detail))
                .setText("Post " + MessageController.getInstance().comments.get(0).getPostid()
                        + ", " + MessageController.getInstance().comments.size() + " comments");

    }

    @Override
    public void onDestroy() {
        this.monitor.disable();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
    // initializing and creating recycler view
    private void initializeRecyclerView(){
        rvData = findViewById(R.id.comment_view);
        CommentAdapter adapter = new CommentAdapter(MessageController.getInstance().comments);
        mAdapter = adapter;
        rvData.setAdapter(adapter);
        rvData.setLayoutManager(new LinearLayoutManager(this));
    }

}
