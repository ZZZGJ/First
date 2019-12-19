package com.example.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    private DownloadBinder mBinder = new DownloadBinder();

    class DownloadBinder extends Binder{
        public void startDownload(){
            Log.d("MyService","startDownloadExecute");
        }
        public int getProgress(){
            Log.d("MyService","getProgressExecute");
            return 0;
        }
    }

    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    public void onCreate(){
        super.onCreate();
        Log.d("MyService","创建");
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        Notification notification = new Notification.Builder(this)
                .setContentText("this is content text")
                .setContentTitle("this is content title")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
                .build();
        notificationManager.notify(1,notification);
        startForeground(1,notification);
    }

    public int onStartCommand(Intent intent,int flags,int startId){
        Log.d("MyService","运行");
        return super.onStartCommand(intent,flags,startId);
    }//用onCreate的方法启动服务时的服务执行的动作

    public void onDestroy(){
        Log.d("MyService","销毁");
        super.onDestroy();
    }


}

