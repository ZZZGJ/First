package com.example.broadcast_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.service.media.MediaBrowserService;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NetworkChangeReceiver networkChangeReceiver;
    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);
    }
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }
    class NetworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
            Toast.makeText(context,"network changed",Toast.LENGTH_SHORT).show();
            if (networkInfo!=null&&networkInfo.isAvailable()){
                Toast.makeText(context,"network is available",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(context,"network is not available",Toast.LENGTH_LONG).show();
            }
        }
    }
}
