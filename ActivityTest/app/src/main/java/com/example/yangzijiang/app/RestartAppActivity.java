package com.example.yangzijiang.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yangzijiang.activitytest.LogUtil;
import com.example.yangzijiang.activitytest.R;
import com.jakewharton.processphoenix.ProcessPhoenix;

import java.io.File;
import java.io.IOException;

public class RestartAppActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "RestartAppActivity";

    private static String processPphoenixDirName = "process_phoenix";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restart_app);

        Log.e(TAG, "RestartAppActivity onCreate");

        Button button = (Button)findViewById(R.id.restart_app);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        restartAppByPendingIntent(this);
//        restartAppByStartActivity();
//        RestartAppActivity.doRestart(this);


        makeProcessPphoenixDir(this);
        restartByProcessPhoenix(this);
    }

    //  this library ProcessPhoenix by Jack Wharton does it better and quickly
    // 注：<intent-filter>属性中必须加上<category android:name="android.intent.category.DEFAULT"/>
    public void restartByProcessPhoenix(Context context){
        ProcessPhoenix.triggerRebirth(context);
    }

    // use PendingIntent to setup launching your start activity in the future and then close your application
    // https://stackoverflow.com/questions/6609414/how-to-programmatically-restart-android-app
    // 【restart app success, but much slower than use ProcessPhoenix】
    public void restartAppByPendingIntent(Context context){
        Intent mStartActivity = new Intent(context, RestartAppActivity.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 0, mPendingIntent);
        System.exit(0);
    }



//    public static void triggerRebirth(Context context, Intent nextIntent) {
//        Intent intent = new Intent(context, RestartAppActivity.class);
//        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra(KEY_RESTART_INTENT, nextIntent);
//        context.startActivity(intent);
//        if (context instanceof Activity) {
//            ((Activity) context).finish();
//        }
//
//        Runtime.getRuntime().exit(0);
//    }

    // This solution is nice, but it will delay for few seconds till it restarts your application, even if you decreased the 100 Millis.
    // However, this library ProcessPhoenix by Jack Wharton does it better and quickly, but its not worth adding library for only this function inside the app.
    public static void doRestart(Context c) {
        try {
            //check if the context is given
            if (c != null) {
                //fetch the packagemanager so we can get the default launch activity
                // (you can replace this intent with any other activity if you want
                PackageManager pm = c.getPackageManager();
                //check if we got the PackageManager
                if (pm != null) {
                    //create the intent with the default start activity for your application
                    Intent mStartActivity = pm.getLaunchIntentForPackage(
                            c.getPackageName()
                    );
                    if (mStartActivity != null) {
                        mStartActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        //create a pending intent so the application is restarted after System.exit(0) was called.
                        // We use an AlarmManager to call this intent in 100ms
                        int mPendingIntentId = 223344;
                        PendingIntent mPendingIntent = PendingIntent
                                .getActivity(c, mPendingIntentId, mStartActivity,
                                        PendingIntent.FLAG_CANCEL_CURRENT);
                        AlarmManager mgr = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
                        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                        //kill the application
                        System.exit(0);
                    } else {
                        Log.e(TAG, "Was not able to restart application, mStartActivity null");
                    }
                } else {
                    Log.e(TAG, "Was not able to restart application, PM null");
                }
            } else {
                Log.e(TAG, "Was not able to restart application, Context null");
            }
        } catch (Exception ex) {
            Log.e(TAG, "Was not able to restart application");
        }
    }
    
    // 【restart failure】http://blog.csdn.net/ecaifu800/article/details/38872381
    public void restartAppByStartActivity(){
        System.exit(0);
        Intent i =new Intent(getBaseContext(), AppCompatActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    // process_phoenix目录，在重启应用之前创建
    public static void makeProcessPphoenixDir(Context context){
        String processPhoenixPath = getProcessPphoenixPath(context);
        File file = new File(processPhoenixPath);
        if (!file.exists()) {
            boolean result = file.mkdir();
            LogUtil.e(TAG, "processPhoenixPath = " + processPhoenixPath + ". mkdir result = " + String.valueOf(result));
        }
    }

    //
    public static String getProcessPphoenixPath(Context context){
        String processPhoenixPath = null;
        try {
            processPhoenixPath = context.getFilesDir().getParentFile().getCanonicalPath() + File.separator + processPphoenixDirName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return processPhoenixPath;
    }
}
