package com.example.contractstest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView)findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        Button button = (Button)findViewById(R.id.button_1);
        Button button2 = (Button)findViewById(R.id.button_2);
        button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS},1);
            }else {
                readContracts();
            }
        }
    });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_CONTACTS},2);
                }else {
                    insertContracts();
                }
            }
        });

}

    protected void readContracts(){
        Cursor cursor = null;
        String name;
        String number;
        try {
            list.clear();
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            if (cursor != null){
                while(cursor.moveToNext()){
                    name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    list.add(name+"    "+number);
                }
                adapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (cursor != null){
                cursor.close();
            }
        }
    }

    protected void insertContracts(){
        Cursor cursor = null;
        String name;
        String number;
        try {
            Toast.makeText(MainActivity.this,"successful",Toast.LENGTH_LONG).show();
            ContentValues contentValues = new ContentValues();
            contentValues.put(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,"11adad");
            contentValues.put(ContactsContract.CommonDataKinds.Phone.NUMBER,10086);
            getContentResolver().insert(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,contentValues);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        switch (requestCode){
            case 1:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    readContracts();
                }
            case 2:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    insertContracts();
                }
                else {
                    Toast.makeText(this,"权限获取失败",Toast.LENGTH_LONG).show();
                }
        }
    }

}
