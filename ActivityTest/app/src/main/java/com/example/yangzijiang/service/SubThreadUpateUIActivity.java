package com.example.yangzijiang.service;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yangzijiang.activitytest.R;

public class SubThreadUpateUIActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;

    public static final int UPDATE_TEXT = 1;

    //  异步消息发送和处理者，能在子线程发送消息，在主线程中捕获
    private Handler handler = new Handler(){

        public void handleMessage(Message msg) {  // 处理异步消息
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATE_TEXT:
                    textView.setText("change");  // 在此更新UI，这是在主线程
                    break;
                default:
                    break;
            }
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_thread_upate_ui);

        Button button = (Button) findViewById(R.id.button8);
        button.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.textView3);

    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button8:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        textView.setText("change text");  // 直接在子线程中修改控件属性，会报错

                        Message message = new Message();  // 能在线程之间传递的消息
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);  // 发送的消息，在主线程里的handleMessage方法里被捕获
                    }
                }).start();
                break;
            default:
                break;
        }
    }
}
