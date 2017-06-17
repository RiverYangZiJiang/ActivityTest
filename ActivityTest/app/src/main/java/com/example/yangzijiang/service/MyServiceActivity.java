package com.example.yangzijiang.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yangzijiang.activitytest.R;
import android.support.v7.app.AppCompatActivity;

/*测试服务用，如启动服务、停止服务*/
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

        Button startService = (Button) findViewById(R.id.start_service);
        startService.setOnClickListener(this);

        Button stopService = (Button) findViewById(R.id.stop_service);
        stopService.setOnClickListener(this);

        Button bindService = (Button) findViewById(R.id.bind_service);
        bindService.setOnClickListener(this);

        Button unbindService = (Button) findViewById(R.id.unbind_service);
        unbindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_service:
                Intent startItent = new Intent(this, MyService.class);
                startService(startItent);  // 开启任务
                break;
            case R.id.stop_service:
                Intent stopItent = new Intent(this, MyService.class);
                stopService(stopItent);  // 停止任务
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
