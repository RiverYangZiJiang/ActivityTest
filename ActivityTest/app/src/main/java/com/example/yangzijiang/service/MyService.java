package com.example.yangzijiang.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.yangzijiang.activitytest.LogUtil;

/*服务是android中实现程序后台运行的解决方案，非常适合执行不需要和用户交互而且还要长期运行的任务。服务能够在后台执行任务，默认在主线程执行*/
public class MyService extends Service {
    private static final String TAG = "MyService";
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d(TAG, "onStartCommand" + Thread.currentThread());
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG, "onDestroy");
    }
}
