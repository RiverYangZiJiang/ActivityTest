package com.example.yangzijiang.activitytest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;
/**
 * Created by yangzijiang on 24/05/2017.
 */

public class MyEncryptDatabaseHelper extends SQLiteOpenHelper{
    private Context mContext;

    private static final String TAG = "MyEncryptDatabaseHelper";

    public MyEncryptDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        Log.d(TAG, "MyDatabaseHelper: ");

        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: ");
        db.execSQL(CREATE_BOOK2);
        Toast.makeText(mContext, "onCreate CREATE_BOOK2 success", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private static final String CREATE_BOOK2 = "create table Book2 ("
            + "id integer primary key autoincrement,"
            + "author text,"
            + "price real,"
            + "pages integer, "
            + "name)";
}
