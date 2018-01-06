package com.example.yangzijiang.db;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by yangzijiang on 22/05/2017.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context mContext;
    private static final String TAG = "MyDatabaseHelper";
    
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        Log.d(TAG, "MyDatabaseHelper: ");


        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: ");
        db.execSQL(CREATE_BOOK);
        Toast.makeText(mContext, "onCreate CREATE_BOOK success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: ");
        db.execSQL(CREATE_BOOK1);
        Toast.makeText(mContext, "onUpgrade CREATE_BOOK1 success", Toast.LENGTH_SHORT).show();

    }

    private static final String CREATE_BOOK = "create table Book ("
            + "id integer primary key autoincrement,"
            + "author text,"
            + "price real,"
            + "pages integer, "
            + "name)";
    private static final String CREATE_BOOK1 = "create table Book1 ("
            + "id integer primary key autoincrement,"
            + "author text,"
            + "price real,"
            + "pages integer, "
            + "name)";


}
