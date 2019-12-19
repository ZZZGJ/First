package com.example.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView)findViewById(R.id.text_view);
        final TextView textTime = (TextView)findViewById(R.id.text_time);
        final EditText editText = (EditText)findViewById(R.id.edit_text);
        final Button button = (Button)findViewById(R.id.button);
        final Button button_time = (Button)findViewById(R.id.button_time);
        button.setEnabled(false);
        final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        final Button buttonMan = (Button)findViewById(R.id.buttonMan);
        final Button buttonWoman = (Button)findViewById(R.id.buttonWoman);
        final Switch switch_1 = (Switch)findViewById(R.id.switch_1);
        switch_1.setChecked(false);
        editText.setEnabled(false);
        final CheckBox checkBox1 = (CheckBox)findViewById(R.id.checkbox1);
        final CheckBox checkBox2 = (CheckBox)findViewById(R.id.checkbox2);
        final CheckBox checkBox3 = (CheckBox)findViewById(R.id.checkbox3);
        final Button button1 = (Button)findViewById(R.id.buttonCB);
        final SeekBar seekBar = (SeekBar)findViewById(R.id.seekbar);

        buttonMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"你是男生",Toast.LENGTH_SHORT).show();
            }
        });

        buttonWoman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"你是女生",Toast.LENGTH_SHORT).show();
            }
        });

        switch_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    button.setEnabled(true);
                    editText.setEnabled(true);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            textView.setText(editText.getText());
                        }
                    });
                }
                else{
                    editText.setEnabled(false);
                    editText.setText("");
                    textView.setText("我是文本框");
                    button.setEnabled(false);
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String choose = "";
                if(checkBox1.isChecked())choose +=checkBox1.getText().toString();
                if(checkBox2.isChecked())choose +=checkBox2.getText().toString();
                if(checkBox3.isChecked())choose +=checkBox3.getText().toString();
                Toast.makeText(MainActivity.this,choose,Toast.LENGTH_SHORT).show();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(MainActivity.this,"当前进度:"+progress+"/100",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this,"你点我了",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this,"你放手了",Toast.LENGTH_SHORT).show();
            }
        });
        button_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                textTime.setText(df.format(new Date()));
            }
        });

    }
}
