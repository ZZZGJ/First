package com.example.data_demo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    String saved_data="你刚刚输入的内容";
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    String returnData = data.getStringExtra("data_return");
                    Log.d("MainActivity", returnData);
                }
                break;
                default:
        }
    }
    protected void onSaveInstanceState(Bundle data){
        super.onSaveInstanceState(data);
        data.putString("data_key","saved_data");
    }//对数据进行临时保存在键为data_key中
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        Button button1 =(Button)findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("data_be_saved",saved_data);
                startActivityForResult(intent,1);
            }
        });

        if(savedInstanceState!=null){
            String data=savedInstanceState.getString("data_key");
            Log.d("MainActivity",data);
        }
    }

}
