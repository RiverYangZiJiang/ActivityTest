package com.example.yangzijiang.file;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.yangzijiang.activitytest.R;

public class SharedPreferencesActivity extends AppCompatActivity {

    private static final String TAG = "SharedPreferencesActivi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        getDefaultSharedPreferencesTest();
        getSharedPreferencesTest();
    }


    // Context类中的getSharedPreferences方法，第一个参数是文件名，第二个参数是操作模式
    public void getSharedPreferencesTest(){
        // 保存数据
        // /data/data/PackageName/shared_prefs/data.xml文件
        SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("age", 10);
        editor.putString("name", "abcd");
        editor.putBoolean("married", false);
        editor.apply();  // 提交

        // 获取数据
        String name = preferences.getString("name", "");
        int age = preferences.getInt("age", 0);
        Log.d(TAG, "getSharedPreferencesTest: name = " + name);
        Log.d(TAG, "getSharedPreferencesTest: age = " + String.valueOf(age));

    }

    // PreferenceManager类的getDefaultSharedPreferences()方法，以 包名+"preferences" 来命名SharedPreferences文件
    public void getDefaultSharedPreferencesTest(){
        // 保存数据
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("age", 10);
        editor.putString("name", "abc");
        editor.putBoolean("married", false);
        editor.apply();

        // 获取数据
        String name = preferences.getString("name", "");
        Log.d(TAG, "getDefaultSharedPreferencesTest: " + name);
    }

}
