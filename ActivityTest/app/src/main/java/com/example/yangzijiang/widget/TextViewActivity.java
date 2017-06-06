package com.example.yangzijiang.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.yangzijiang.activitytest.LogUtil;
import com.example.yangzijiang.activitytest.R;

/*类似于iOS的UILabel，用于在界面上显示一段文本信息*/
public class TextViewActivity extends AppCompatActivity {
    private static final String TAG = "TextViewActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

        TextView textView = (TextView) findViewById(R.id.textView);
        String inputText = textView.getText().toString();
        LogUtil.d(TAG, "onCreate: " + inputText);
    }
}
