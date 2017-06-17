package com.example.yangzijiang.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/*显示Intent，显示意图，即明确指定要启动哪一个类*/
public class ExplicitIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit_intent);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.Explicit_Intent:
                Intent intent = new Intent(this, SecondActivity.class);  // 明确指定SecondActivity类
                intent.putExtra("Explicit_Intent", "message");  // 传递数据给下一个活动
//                startActivity(intent);
                startActivityForResult(intent, 1);  // 可以从下一个活动返回数据到本活动
                break;
            case R.id.Implicit_Intent:
                Intent intent1 = new Intent("android.intent.action.ACTION_START123");  // 隐式指定满足条件的类
                intent1.addCategory("android.intent.category.MY_CATEGORY");  // 明确指定category
                startActivity(intent1);
                break;
            case R.id.open_system_browser:
                Intent intent2 = new Intent("android.intent.action.VIEW");  // android.intent.action.VIEW为系统默认action，用来打开系统浏览器
                intent2.setData(Uri.parse("http://www.bing.com"));  // setData指定当前Intent操作的数据
                startActivity(intent2);
                break;
            case R.id.dial_tel:
//                Intent intent3 = new Intent(Intent.ACTION_CALL);  // 打电话，要用真机
                Intent intent3 = new Intent(Intent.ACTION_DIAL);  // 打开拨号盘
                intent3.setData(Uri.parse("tel:10086"));
                startActivity(intent3);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  // 获取下一个活动返回的数据
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK){
            String message = data.getStringExtra("data_return");
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
