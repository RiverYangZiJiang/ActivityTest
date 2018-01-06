package com.example.yangzijiang.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import com.example.yangzijiang.activitytest.R;

import java.io.File;
/*android的通话类似于iOS的本地通知，android的广播类似于iOS的通知*/
public class NotificationActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Button button = (Button)findViewById(R.id.button7);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button7:
                // 获得系统通知管理器
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                // 创建待处理意图，只有待处理意图的通知才能点击。HandleNotificationActivity是点击状态栏通知要打开的活动
                Intent intent = new Intent(this, HandleNotificationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
                // 创建通知对象
                Notification notification = new NotificationCompat.Builder(this)
                        .setContentTitle("This is content title")
                        .setContentText("This is content text")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentIntent(pendingIntent)  // 设置待处理意图
                        .setAutoCancel(true)  // 设置点击通知之后，通知自动在状态栏消失
                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))   // 设置声音
                        .setVibrate(new long[]{0, 1000, 2000, 3000})  // 设置震动
                        .setLights(Color.GREEN, 1000, 1000)   // 设置闪光灯
                        .setPriority(NotificationCompat.PRIORITY_MAX)  // 设置通知为最高级别，不管用户在玩游戏还是看电影，这条通知都会显示在最上方
                        .build();
                // 通过通知管理器发送通知
                manager.notify(1, notification);  // 第一个参数是通知id，要保证每个通知id唯一
                break;
            default:
                break;
        }
    }
}
