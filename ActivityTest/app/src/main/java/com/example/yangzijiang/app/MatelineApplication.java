package com.example.yangzijiang.app;

import java.util.List;

import com.example.mylibrary.ProcessPhoenix;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;


/**
 * Created by yangzijiang on 07/06/2017.
 */

public class MatelineApplication extends Application {
    private static final String TAG = "MatelineApplication";

    public static boolean isApplicationInit = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Context context = this.getApplicationContext();
        Log.e(TAG, "MatelineApplication onCreate ProcessName = " + getProcessName() + "  context = " + context.toString() + "\n context identityHashCode = " + System.identityHashCode(context));

        if (ProcessPhoenix.isPhoenixProcess(context)){
            return;
        }

        Log.e(TAG, "isPhoenixProcess result = " + String.valueOf(ProcessPhoenix.isPhoenixProcess(context)));


        if ("com.example.yangzijiang.activitytest".equals(getProcessName())){
            Log.e(TAG, "\"com.example.yangzijiang.activitytest\".equals(getProcessName())");
        }

        if (!isApplicationInit){
            isApplicationInit = true;
            Log.e(TAG, "isApplicationInit = " + String.valueOf(isApplicationInit));
        }

        // initialize logger
        Logger.addLogAdapter(new AndroidLogAdapter());
    }



    /**
     *
     * 根据进程获取进程名字<br>
     * (功能详细描述)<br>
     *
     * @author l00208306
     * @see 相关函数，对于重要的函数建议注释
     * @since MatelineV100R001C00, 2015年1月29日
     * @param cxt
     * @param pid
     * @return
     */
    public String getProcessName()
    {
        int pid = android.os.Process.myPid();
//        LOGGER.info("getProcessName -- pid:" + pid);
        ActivityManager am = (ActivityManager)this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null)
        {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps)
        {
//            LOGGER.info("getProcessName -- processName:" + procInfo.processName);
            for (String a : procInfo.pkgList)
            {
//                LOGGER.info("getProcessName -- pkg:" + a);
            }
            if (procInfo.pid == pid)
            {
                return procInfo.processName;
            }
        }
        return null;
    }
}
