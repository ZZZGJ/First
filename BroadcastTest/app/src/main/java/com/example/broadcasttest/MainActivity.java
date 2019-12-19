package com.example.broadcasttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    protected MyReceiver myReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("1");
        myReceiver = new MyReceiver();
        registerReceiver(myReceiver,intentFilter);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("1");
                String string = "听得到吗";
                intent.putExtra("string_key",string);
                sendBroadcast(intent);
            }
        });
    }
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String string = intent.getStringExtra("string_key");
            Log.d("context",string);
            Toast.makeText(context, "能别点了吗？", Toast.LENGTH_LONG).show();
        }
    }
}
