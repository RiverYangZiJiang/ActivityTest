package com.example.yangzijiang.activitytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
/*构建简单的界面 https://developer.android.google.cn/training/basics/firstapp/building-ui*/
/*主 Activity（您的应用的入口点），当您构建和运行应用时，系统会启动此 Activity 的实例并加载其布局。*/
public class MainActivity extends AppCompatActivity {
    // 公共常量。为 intent extra 定义键时最好使用应用的软件包名称作为前缀。这可以确保在您的应用与其他应用交互时这些键始终保持唯一。
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send button 函数必须声明以下内容：公共访问；空返回值；
     * 以 View 作为唯一参数（它是之前点击的 View 对象）*/
    public void sendMessage(View view) {
        // Do something in response to button
        // 您可以使用 intent 从一个组件启动另一个组件。甚至，您还可以启动不同应用中的组件，例如，启动地图应用中的 Activity 以显示地址。
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText3);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);  // 启动 Intent 指定的 DisplayMessageActivity 实例
    }
}

