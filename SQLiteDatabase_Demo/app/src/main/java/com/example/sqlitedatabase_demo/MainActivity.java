package com.example.sqlitedatabase_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;

public class MainActivity extends AppCompatActivity {
private MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabase = new MyDatabase(this,"database",null,1);
        Button button = (Button)findViewById(R.id.button_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabase.getWritableDatabase();
//                Toast.makeText(MainActivity.this,"成功创建数据库",Toast.LENGTH_LONG).show();
            }
        });
    }
}