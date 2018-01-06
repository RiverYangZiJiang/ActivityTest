package com.example.yangzijiang.webview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.URLUtil;

import com.example.yangzijiang.activitytest.LogUtil;
import com.example.yangzijiang.activitytest.R;

public class UrlActivity extends AppCompatActivity {

    private static final String TAG = "UrlActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        validateUrl();
    }

    public void validateUrl(){
        boolean result = URLUtil.isValidUrl("adb shell");  // false
        boolean result1 = URLUtil.isValidUrl("127.0.0.1");  // false
        boolean result2 = URLUtil.isValidUrl("/data/data/com.huawei.mateline.mobile/12c.text");  // false
        boolean result3 = URLUtil.isValidUrl("http://127.0.0.1");  // true
        boolean result4 = URLUtil.isValidUrl("file://123.text");  // true
        LogUtil.d(TAG, "result = " + result + "， result1 = " + result1 + ", result2 = " + result2);
        LogUtil.d(TAG, "result3 = " + result3 + "， result4 = " + result4 + ", result2 = " + result2);
     }
}
