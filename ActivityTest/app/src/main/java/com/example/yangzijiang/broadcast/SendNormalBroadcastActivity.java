package com.example.yangzijiang.broadcast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.yangzijiang.activitytest.R;

public class SendNormalBroadcastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_normal_broadcast);
    }


    public void sendNormalBroadcast(View view){
        Intent intent = new Intent("com.example.yangzijiang.broadcast.MBroadcast");
        sendBroadcast(intent);  // 发送的是全局广播，可以被其他应用接收到
    }
}
