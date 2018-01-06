package com.example.yangzijiang.encode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;

import com.example.yangzijiang.activitytest.LogUtil;
import com.example.yangzijiang.activitytest.R;

public class Base64Activity extends AppCompatActivity {

    private static final String TAG = "Base64Activity";
    private static boolean isActivityCreated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 放在前面会报" did not call through to super.onCreate()"，然后崩溃
//        if (isActivityCreated){
//            return;
//        }else {
//            isActivityCreated = true;
//        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base64);

//        if (isActivityCreated){
//            return;
//        }else {
//            isActivityCreated = true;
//        }

        Intent intent = getIntent();
        intent.getBooleanExtra("needlogin", false);

        // 不管是直接点击打开，还是IntentSuit里sDos攻击，结果都为null，getCallingPackage() = null, getCallingActivity() = null
        LogUtil.d(TAG, "getCallingPackage() = " + getCallingPackage() + ", getCallingActivity() = " + getCallingActivity());
        base64Encode();
    }

    /**
     * base64编码
     */
    public void base64Encode(){
        String str = "123456";
        // 末尾会带换行符"MTIzNDU2\n"
        String base64Str = Base64.encodeToString(str.getBytes(), Base64.DEFAULT);
        // 末尾无换行符"MTIzNDU2\n"
        String base64Str1 = Base64.encodeToString(str.getBytes(), Base64.NO_WRAP);
        LogUtil.d(TAG, "base64Str = " + base64Str);
    }
}


