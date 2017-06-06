package com.example.yangzijiang.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yangzijiang.activitytest.LogUtil;
import com.example.yangzijiang.activitytest.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPURLConnectionActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "HTTPURLConnectionActivi";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpurlconnection);

        Button button = (Button)findViewById(R.id.send_request);
        button.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.response_text);

    }

    @Override
    public void onClick(View v) {
        LogUtil.d(TAG, "onClick");
        if (v.getId() == R.id.send_request){
            sendRequestWithHTTPURLConnect();
        }
    }

    private void  sendRequestWithHTTPURLConnect(){
        LogUtil.d(TAG, "sendRequestWithHTTPURLConnect");
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
//                    connection.setRequestMethod("GET");  // 发起get请求

                    connection.setRequestMethod("POST");  // 发起post请求
                    DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());  // 输入POST请求的参数
                    outputStream.writeBytes("username=admin&password=123456");

                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();  // 从网络获取输入流

                    // 对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder builder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        builder.append(line);
                    }
                    showResponse(builder.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null){
                        try{
                            reader.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }

                        if (connection != null){
                            connection.disconnect();
                        }
                    }

                }

            }
        }).start();
    }

    private void showResponse(final String text){
        LogUtil.d(TAG, "showResponse : " + text);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(text);
            }
        });
    }
}
