package com.example.yangzijiang.activitytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LogUtilTestActivity extends AppCompatActivity {

    private static final String TAG = "LogUtilTestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_util_test);

        boolean isApkDebugable = LogUtil.isApkDebugable(this);
        LogUtil.d(TAG, "isApkDebugable = " + String.valueOf(isApkDebugable));
    }
}
