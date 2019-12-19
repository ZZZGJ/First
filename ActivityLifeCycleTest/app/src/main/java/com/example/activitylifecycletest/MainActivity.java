package com.example.activitylifecycletest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1=(Button)findViewById(R.id.start_normal_activity);
        Button button2=(Button)findViewById(R.id.start_dialog_activity);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Normal_Activity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Dialog_Activity.class);
                startActivity(intent);
            }
        });
        };
    protected void onStart(){
        super.onStart();
        Log.d("MainActivity","onStart");
    }
    protected void onResume(){
        super.onResume();
        Log.d("MainActivity","onResume");
    }
    protected void onPause(){
       super.onPause();
        Log.d("MainActivity","onPause");
    }
    protected void onStop(){
        super.onStop();
        Log.d("MainActivity","onStop");
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.d("MainActivity","onDestroy");
    }
    protected void onRestart(){
        super.onRestart();
        Log.d("MainActivity","onRestart");
    }
}
