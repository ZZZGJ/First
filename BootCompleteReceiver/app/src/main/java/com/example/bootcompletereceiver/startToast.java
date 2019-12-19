package com.example.bootcompletereceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class startToast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "开机啦", Toast.LENGTH_SHORT).show();
        Log.d("startToast","开机啦");
    }
}
