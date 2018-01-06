package com.example.yangzijiang.widget;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.yangzijiang.activitytest.LogUtil;
import com.example.yangzijiang.activitytest.R;

// ProgressDialog不但可以不断转圈的圆形进度条，还可以按百分比显示水平进度条
public class ProgressDialogActivity extends AppCompatActivity {

    private static final String TAG = "ProgressDialogActivity";

    final static int MAX_PROGRESS = 100;
    private int[] data = new int[100];
    int progressStatus = 0;
    int hasData = 0;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_dialog);

        LogUtil.d(TAG, "move_up_to_cancel = " + getResources().getString(R.string.move_up_to_cancel));
    }

    public void onClick1(View view){
        showProgressDialog();
    }

    // 使用new ProgressDialog()创建进度条，会一直转圈
    private void showProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(ProgressDialogActivity.this);
        progressDialog.setTitle("progressDialog");
        progressDialog.setMessage("loading...");
        progressDialog.setCancelable(true);  // 为true，点击屏幕其他地方本ProgressDialog会消失；为false，不会消失
        progressDialog.show();
    }

    public void onClick2(View view){
        // 使用静态方法调用环形进度条，会一直转圈
        ProgressDialog.show(this, "ProgressDialog", "loading...", false, true);
    }

    public void onClick3(View view){
        progressStatus = 0;
        hasData = 0;
        pd = new ProgressDialog(this);
        pd.setMax(MAX_PROGRESS);
        pd.setMessage("耗时任务百分比");
        pd.setCancelable(false);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 设置对话框的进度条是否显示进度
        pd.setIndeterminate(false);
        pd.show();
        new Thread(){
            public void run()
            {
                while(progressStatus < MAX_PROGRESS){
                    progressStatus = MAX_PROGRESS * doWork() / data.length;
                    handler.sendEmptyMessage(0x123);

                    if (progressStatus >= MAX_PROGRESS)
                    {
                        pd.dismiss();
                    }
                }
            }
        }.start();
    }

    private int doWork(){
        data[hasData++] = (int) (Math.random() * 100);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return hasData;
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123){
                pd.setProgress(progressStatus);
            }
        }
    };

}
