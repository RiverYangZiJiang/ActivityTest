package com.example.yangzijiang.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;


import com.example.yangzijiang.activitytest.LogUtil;
import com.example.yangzijiang.activitytest.R;

public class MyServiceActivity extends AppCompatActivity implements View.OnClickListener{

    // DownloadBinder为继承自Binder的自定义类
    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("onServiceConnected", "");
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();  // 活动调用服务里的方法
            downloadBinder.getProgress();  // 活动调用服务里的方法
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate", "onCreate");

        setContentView(R.layout.activity_my_service);

        Button startService = (Button) findViewById(R.id.button9);
        startService.setOnClickListener(this);

        Button stopService = (Button) findViewById(R.id.button10);
        stopService.setOnClickListener(this);

        Button bindService = (Button) findViewById(R.id.bind_service);
        bindService.setOnClickListener(this);

        Button unbindService = (Button) findViewById(R.id.unbind_service);
        unbindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button9:
                Intent startItent = new Intent(this, MyService.class);
                startService(startItent);
                break;
            case R.id.button10:
                Intent stopItent = new Intent(this, MyService.class);
                stopService(stopItent);
                break;
            case R.id.bind_service:
                Intent bindItent = new Intent(this, MyService.class);
                bindService(bindItent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;
            default:
                break;
        }
    }
}
