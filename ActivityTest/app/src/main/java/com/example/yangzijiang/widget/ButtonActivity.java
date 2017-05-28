package com.example.yangzijiang.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yangzijiang.activitytest.R;

// 以实现View.OnClickListener接口的方式来进行响应按钮点击事件的注册
public class ButtonActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "ButtonActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button3:
                Log.d(TAG, "onClick: button3");
        }
    }
}
