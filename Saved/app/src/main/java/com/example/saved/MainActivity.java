package com.example.saved;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    FileOutputStream fileOutputStream;
    BufferedWriter bufferedWriter;
    FileInputStream inputStream;
    BufferedReader bufferedReader = null;
    StringBuilder stringBuilder = new StringBuilder();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        editText = (EditText)findViewById(R.id.Edittext);
        String last = load();
        if (!TextUtils.isEmpty(last)){
            editText.setText(last);
            editText.setSelection(last.length());
            Toast.makeText(this,"恢复上次输入",Toast.LENGTH_LONG);
        }
    }

    protected void save(String input) {
        try {
            fileOutputStream = openFileOutput("文件", MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        String data = editText.getText().toString();
        save(data);
    }

    public String load(){
        try {
            inputStream = openFileInput("文件");
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String string = null;
            while ((string = bufferedReader.readLine()) != null){
                stringBuilder.append(string);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (bufferedReader != null){
                    bufferedReader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
