package com.example.yangzijiang.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.yangzijiang.activitytest.R;
/*android的广播类似于iOS的通知，android的通知类似于iOS的本地通知。
* 广播是一种可以跨进程的通信方式，全局广播，可以被其他应用接收到
* */
public class BroadcastActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private NetworkChangeReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");  // 要监听什么广播，就在此添加对应的action
        receiver = new NetworkChangeReceiver();
        registerReceiver(receiver, intentFilter);   // 注册广播接收器
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);  // 动态注册的广播接收器一定要取消注册
    }

    // 自定义广播接收器类：创建类继承自BroadcastReceiver，重写onReceive方法
    class NetworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {

            // ConnectivityManager是一个系统服务类，专门用于管理网络连接的
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()){
                Toast.makeText(context, "network is available", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_LONG).show();
            }
        }
    }
}
