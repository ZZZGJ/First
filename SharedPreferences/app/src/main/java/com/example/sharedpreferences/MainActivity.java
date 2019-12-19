package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button_1);
        Button button2 =(Button)findViewById(R.id.button_2);
                button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor sharedPreferences = getSharedPreferences("新文件夹",MODE_PRIVATE).edit();
                sharedPreferences.putString("name","Tom");
                sharedPreferences.putBoolean("alive",true);
                sharedPreferences.putInt("age",10);
                sharedPreferences.apply();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("新文件夹",MODE_PRIVATE);
                String name = sharedPreferences.getString("name","");
                Log.d("name",name);
            }
        });
    }
}
