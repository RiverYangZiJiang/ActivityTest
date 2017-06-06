package com.example.yangzijiang.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yangzijiang.activitytest.R;
/*类似于iOS的TextView，允许用户在控件里输入和编辑内容。不能在布局文件的design拖入到界面，而是要在布局文件的Text模式在xml中输入代码*/
public class EditTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
    }
}
