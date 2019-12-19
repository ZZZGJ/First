package com.example.providertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button add = (Button)findViewById(R.id.button_1);
        Button query = (Button)findViewById(R.id.button_2);
        Button update = (Button)findViewById(R.id.button_3);
        Button delete = (Button)findViewById(R.id.button_4);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.sqlitedatabase_demo.DatabaseProvider/book");
                ContentValues values = new ContentValues();
                values.put("name","aoligei");
                values.put("author","dongyongguaige");
                values.put("pages",100);
                values.put("price",10);
                getContentResolver().insert(uri,values);
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.sqlitedatabase_demo.DatabaseProvider/book");
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                if(cursor != null){
                    while (cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        int price = cursor.getInt(cursor.getColumnIndex("price"));
                        Log.d("MainActivity","the name is"+name);
                        Log.d("MainActivity","the author is"+author);
                        Log.d("MainActivity","the pages is"+pages);
                        Log.d("MainActivity","the price is"+price);
                    }
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.sqlitedatabase_damo.DatabaseProvider/book");
                ContentValues values = new ContentValues();
                values.put("name","yigeiwoligiao");
                values.put("author","giaoge");
                values.put("price","20");
                values.put("pages","200");
                getContentResolver().update(uri,values,null,null);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.sqlitedatabase_demo.DatabaseProvider/book");
                getContentResolver().delete(uri,null,null);
            }
        });
    }
}
