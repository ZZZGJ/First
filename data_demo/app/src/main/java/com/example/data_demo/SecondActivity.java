package com.example.data_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
@Override
public   void onBackPressed(){
    Intent intent = new Intent();
    intent.putExtra("data_return","他按了返回键");
    setResult(RESULT_OK,intent);
    finish();
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        Intent intent = getIntent();
        String data = intent.getStringExtra("data_be_saved");
        Log.d("SecondActivity",data);
        Button button2 = (Button)findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data_return","这就是数据");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
