package com.example.project2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.project2.Constants.Constants;
import com.example.project2.Managers.MessageController;
import com.example.project2.MessageCenter.NotificationCenter;


@SuppressLint("Registered")
public class MainActivity extends AppCompatActivity implements NotificationCenter.NotificationTarget {
    private static Context applicationContext = null;
    private ConnectionMonitor monitor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationContext = getApplicationContext();

        NotificationCenter.INSTANCE.register(this, Constants.Tasks.FETCH_DATA);


        setContentView(R.layout.activity_main);

        this.monitor = new ConnectionMonitor();

        MessageController.getInstance().getPosts(monitor.getConnection());
        MessageController.getInstance().getComments(MessageController.posts.get(2).getId(), monitor.getConnection());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.monitor.disable();
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


    /**
     * a monitor class for network connectivity
     */
    private class ConnectionMonitor extends ConnectivityManager.NetworkCallback{
        final private NetworkRequest networkRequest;
        private Context context;
        private Boolean isConnected = false;

        ConnectionMonitor() {
            networkRequest = new NetworkRequest.Builder()
                    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                    .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                    .build();
            enable(MainActivity.this);
        }

        Boolean getConnection() {
            return isConnected;
        }

        void enable(Context context) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            cm.registerNetworkCallback(networkRequest, this);
            this.context = context;
            NetworkInfo network = cm.getActiveNetworkInfo();
            if (network == null || !network.isConnected()){

            }
//                Glide.with(context).load(R.drawable.loading).into((ImageView) findViewById(R.id.ivtest));
        }

        void disable() {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            cm.unregisterNetworkCallback(this);
            this.context = null;
        }

        @Override
        public void onAvailable(Network network) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    isConnected = true;
//                    ((ImageView) findViewById(R.id.ivtest)).setImageResource(0);
//                    ((TextView) findViewById(R.id.connecting)).setText("");
//                }
//            });
        }

        @Override
        public void onLost(Network network) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    isConnected = false;
//                    Glide.with(context).load(R.drawable.loading).into((ImageView) findViewById(R.id.ivtest));
//                    ((TextView) findViewById(R.id.connecting)).setText(" connecting...");
//                }
//            });
        }
    }

}

