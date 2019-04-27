package com.example.project2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.project2.Constants.Constants;
import com.example.project2.Managers.Comment;
import com.example.project2.Managers.MessageController;
import com.example.project2.Managers.Post;
import com.example.project2.MessageCenter.NotificationCenter;
import com.example.project2.UIClasses.CommentActivity;
import com.example.project_1.UIComponents.DataAdapter.DataNumberAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("Registered")
public class MainActivity extends AppCompatActivity implements NotificationCenter.NotificationTarget, AdapterView.OnItemSelectedListener {
    private static Context applicationContext = null;
    private DataNumberAdapter mAdapter;
    private RecyclerView rvData;
    private CharSequence[] options = {"list view", "gird view"};
//    private ConnectionMonitor monitor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationContext = getApplicationContext();

        NotificationCenter.INSTANCE.register(this, Constants.Tasks.LOAD_POST);
        NotificationCenter.INSTANCE.register(this, Constants.Tasks.LOAD_COMMENT);

        setContentView(R.layout.activity_main);

        // starting toolbar
//        Toolbar toolbar = findViewById(R.id.main_toolbar);
//        setSupportActionBar(toolbar);

        initializeRecyclerView();
        initializeSpinner();

//        this.monitor = new ConnectionMonitor();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        this.monitor.disable();
        NotificationCenter.INSTANCE.unregister(this, Constants.Tasks.LOAD_POST);
        NotificationCenter.INSTANCE.unregister(this, Constants.Tasks.LOAD_COMMENT);
    }

    // initializing and creating recycler view
    private void initializeRecyclerView(){
        rvData = findViewById(R.id.numList);
        ArrayList<Post> dataNumbers = new ArrayList<>();
        dataNumbers.add(new Post(1,1,"sdf", "asdf"));
        DataNumberAdapter adapter = new DataNumberAdapter(dataNumbers);
        mAdapter = adapter;
        rvData.setAdapter(adapter);
        rvData.setLayoutManager(new LinearLayoutManager(this));
        MessageController.getInstance().getPosts();
    }

    private void initializeSpinner(){
        Spinner spinner = (Spinner) findViewById(R.id.btn3);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, R.layout.support_simple_spinner_dropdown_item, options);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }


    public static Context getContext(){
        return applicationContext;
    }

    @Override
    public void notifiedPosts(int taskID, @NotNull List<Post> posts) {
        MessageController.getInstance().posts = (ArrayList<Post>) posts;
        mAdapter.setMData(posts);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifiedComments(int taskID, @NotNull List<Comment> comments) {
        MessageController.getInstance().comments = (ArrayList<Comment>) comments;
        Intent intent = new Intent(this, CommentActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0){
            rvData.setLayoutManager(new LinearLayoutManager(this));
        }else{
            rvData.setLayoutManager(new GridLayoutManager(this, 2));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

//
//    /**
//     * a monitor class for network connectivity
//     */
//    private class ConnectionMonitor extends ConnectivityManager.NetworkCallback{
//        final private NetworkRequest networkRequest;
//        private Context context;
//        private Boolean isConnected = false;
//
//        ConnectionMonitor() {
//            networkRequest = new NetworkRequest.Builder()
//                    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
//                    .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
//                    .build();
//            enable(MainActivity.this);
//        }
//
//        Boolean getConnection() {
//            return isConnected;
//        }
//
//        void enable(Context context) {
//            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//            cm.registerNetworkCallback(networkRequest, this);
//            this.context = context;
//            NetworkInfo network = cm.getActiveNetworkInfo();
//            if (network == null || !network.isConnected()){
//
//            }
//                Glide.with(context).load(R.drawable.loading).into((ImageView) findViewById(R.id.ivtest));
//        }
//
//        void disable() {
//            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//            cm.unregisterNetworkCallback(this);
//            this.context = null;
//        }
//
//        @Override
//        public void onAvailable(Network network) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    isConnected = true;
//                    ((ImageView) findViewById(R.id.ivtest)).setImageResource(0);
//                    ((TextView) findViewById(R.id.connecting)).setText("");
//                }
//            });
//        }
//
//        @Override
//        public void onLost(Network network) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    isConnected = false;
//                    Glide.with(context).load(R.drawable.loading).into((ImageView) findViewById(R.id.ivtest));
//                    ((TextView) findViewById(R.id.connecting)).setText(" connecting...");
//                }
//            });
//        }
//    }

}

