package com.example.communicationroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToggleButton toggleButton = (ToggleButton)findViewById(R.id.togglebutton);
        Switch switch_1 = (Switch)findViewById(R.id.switch_1);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressbar);
    }
}
