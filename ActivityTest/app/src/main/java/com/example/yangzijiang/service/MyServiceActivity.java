package com.example.yangzijiang.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.yangzijiang.activitytest.R;

/*测试服务用，如启动服务、停止服务*/
public class MyServiceActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);

        Button startServiceButton = (Button) findViewById(R.id.start_service);
        startServiceButton.setOnClickListener(this);

        Button stopServiceButton = (Button) findViewById(R.id.stop_service);
        stopServiceButton.setOnClickListener(this);

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
            default:
                break;
        }
    }
}
