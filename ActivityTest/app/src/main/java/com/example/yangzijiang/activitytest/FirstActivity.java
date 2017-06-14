package com.example.yangzijiang.activitytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class FirstActivity extends AppCompatActivity {

    private static final String TAG = "FirstActivity";
    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        setContentView(R.layout.first_layout);

        //不可忽略的 进行so库加载
//        SQLiteDatabase.loadLibs(this);
//        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);
//        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);
//        Log.d(TAG, "after new MyDatabaseHelper");

        Button button1 = (Button)findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(FirstActivity.this, "hhh", Toast.LENGTH_SHORT).show();
                // 显示Intent，界面跳转
//                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
//                startActivity(intent);

                // 隐式Intent，使用默认的Category
//                Intent intent = new Intent("android.intent.action.ACTION_START");
//                startActivity(intent);

                // 隐式Intent，使用自定义的Category
//                Intent intent = new Intent("android.intent.action.ACTION_START");
//                intent.addCategory("android.intent.category.MY_CATEGORY");
//                startActivity(intent);

                // 隐式Intent，打开系统内置动作，打开系统浏览器
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("http://www.bing.com"));
//                startActivity(intent);

                // 隐式Intent，打开系统内置动作，打开系统拨号盘
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:10086"));
//                startActivity(intent);


//                Intent intent = new Intent(FirstActivity.this, BroadcastActivity.class);
//                startActivity(intent);

                // 发送自定义广播
//                Intent intent = new Intent("com.example.broadcasttest.MY_BRAODCAST");
//                sendBroadcast(intent);

//                Intent intent = new Intent(FirstActivity.this, SaveDataToFileActivity.class);
//                startActivity(intent);

                // 创建非加密数据库
//                Log.d(TAG, "before dbHelper.getWritableDatabase()");
//                dbHelper.getWritableDatabase();
                // 创建加密数据库
                // 如果数据库需要加密升级，则先升级，再打开数据库，否则会崩溃
//                dbHelper.encryptDB("BookStoreEn.db", "BookStore.db", "1234");
//                dbHelper.getWritableDatabase("1234");

//                Intent intent = new Intent(FirstActivity.this, DatabaseTestActivity.class);
//                startActivity(intent);
            }
        });

        Button button2 = (Button)findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, DatabaseTestActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(FirstActivity.this, "add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(FirstActivity.this, "remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }
}
