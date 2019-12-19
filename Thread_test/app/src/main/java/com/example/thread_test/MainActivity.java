package com.example.thread_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button);
        text = (TextView)findViewById(R.id.text);
        button.setOnClickListener(this);
    }
    public void onClick(View e){
        switch(e.getId()){
            case R.id.button:
                new Thread(new Runnable() {
                    public void run() {
                        if (text.getText() == "你看，他没了")
                        text.setText("点了我没了");
                        else
                            text.setText("你看，他没了");
                    }
                }).start();
                break;
            default:
                break;
        }
    }
}
