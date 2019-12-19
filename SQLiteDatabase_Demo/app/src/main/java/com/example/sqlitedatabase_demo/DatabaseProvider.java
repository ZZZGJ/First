package com.example.sqlitedatabase_demo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.QuickViewConstants;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DatabaseProvider extends ContentProvider {
    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;
    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_ITEM = 3;
    public static final String AUTHORITY = "com.example.sqlitedatabas_demo.DatabaseProvider";
    private static UriMatcher uriMatcher;
    private MyDatabase myDatabase;
    static {
        uriMatcher = new UriMatcher(uriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"book",BOOK_DIR);
        uriMatcher.addURI(AUTHORITY,"book/#",BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY,"category",CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY,"category/#",CATEGORY_ITEM);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        SQLiteDatabase db = myDatabase.getWritableDatabase();
        int deleteRow = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                deleteRow = db.delete("Book",selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deleteRow = db.delete("Book","id = ?",new String[]{bookId});
                break;
            case CATEGORY_DIR:
                deleteRow = db.delete("Category",selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                deleteRow = db.delete("Category","id = ?",new String[]{categoryId});
                break;
            default:
                break;
        }
        return deleteRow;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.sqlitedatabase_demo.provider.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.sqlitedatabas_demo.provider.book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.sqlitedatabas_demo.provider.category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.sqlitedatabas_demo.provider.category";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = myDatabase.getWritableDatabase();
        Uri uri1 = null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = db.insert("Book",null,values);
                uri1 = Uri.parse("content://"+AUTHORITY+"/book/"+newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategoryId = db.insert("Category",null,values);
                uri1 = Uri.parse("content://"+AUTHORITY+"/category/"+newCategoryId);
                break;
            default:
                    break;
        }
        return uri1;
    }

    @Override
    public boolean onCreate() {
        myDatabase = new MyDatabase(getContext(),"BookStore.db",null,2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        Cursor cursor = null;
        switch(uriMatcher.match(uri)){
            case BOOK_DIR:
                cursor = db.query("Book",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query("Book",projection,"id = ?",new String[]{bookId},null,null,sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = db.query("Category",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = db.query("Category",projection,"id = ?",new String[]{categoryId},null,null,sortOrder);
                break;
            default:
                    break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = myDatabase.getWritableDatabase();
        int updateRows = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                updateRows = db.update("Book",values,selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                updateRows = db.update("Book",values,"id = ?",new String[]{bookId});
                break;
            case CATEGORY_DIR:
                updateRows = db.update("Category",values,selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                updateRows = db.update("Category",values,"id = ?",new String[]{categoryId});
                break;
            default:
                    break;
        }
        return updateRows;
    }
}
