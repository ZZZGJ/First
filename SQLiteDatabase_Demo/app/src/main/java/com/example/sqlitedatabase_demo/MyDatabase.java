package com.example.sqlitedatabase_demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class MyDatabase extends SQLiteOpenHelper {
    public static String CREATE_BOOK="create table Book("
            +"id integer primary key,"
            +"name text,"
            +"outhor text,"
            +"pages integer,"
            +"price real)";
    Context mcontext;
    public MyDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mcontext = context;
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_BOOK);
        Toast.makeText(mcontext,"成功创建数据库",Toast.LENGTH_LONG).show();
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
}
