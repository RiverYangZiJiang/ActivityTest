package com.example.yangzijiang.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.yangzijiang.activitytest.R;
// 本地广播，发送和接收范围只限于本应用，满足数据安全、高效、不受其他应用广播影响
public class LocalBroadcastActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private LocalBroadcastManager localBroadcastManager;
    private LocalBroadcastReceiver localBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_broadcast);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);  // 获得本地广播管理器

        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.yangzijiang.broadcast.LOCAL");

        localBroadcastReceiver = new LocalBroadcastReceiver();
        localBroadcastManager.registerReceiver(localBroadcastReceiver, intentFilter);  // 使用本地广播管理器注册广播接收器

    }

    public class LocalBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "receive local broadcast", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendLocalBroadcast(View view){
        Intent intent = new Intent("com.example.yangzijiang.broadcast.LOCAL");
        localBroadcastManager.sendBroadcast(intent);  // 使用本地广播管理器发送广播
    }
}
