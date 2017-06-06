package com.example.yangzijiang.activitytest;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

/**
 * Created by yangzijiang on 25/05/2017.
 */

// 自定义日志，可以控制输出哪些级别的日志，开发时设置level为VERBOSE，打印所有日志；发布版本可以设置level为INFO或NOTHING，输出部分日志
public class LogUtil {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    public static int level = VERBOSE;

    public static void v(String tag, String msg){
        if (level <= VERBOSE){
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg){
        if (level <= DEBUG){
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg){
        if (level <= INFO){
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg){
        if (level <= WARN){
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg){
        if (level <= ERROR){
            Log.e(tag, msg);
        }
    }

    public static boolean APP_DBG = false; // 是否是debug模式

    public static void init(Context context){
        APP_DBG = isApkDebugable(context);
    }

    /**
     * 但是当我们没在AndroidManifest.xml中设置其debug属性时:
     * 使用Eclipse运行这种方式打包时其debug属性为true,使用Eclipse导出这种方式打包时其debug属性为法false.
     * 在使用ant打包时，其值就取决于ant的打包参数是release还是debug.
     * 因此在AndroidMainifest.xml中最好不设置android:debuggable属性置，而是由打包方式来决定其值.
     *
     * @param context
     * @return
     * @author SHANHY
     * @date   2015-8-7
     */
    public static boolean isApkDebugable(Context context) {
        try {
            ApplicationInfo info= context.getApplicationInfo();
            return (info.flags&ApplicationInfo.FLAG_DEBUGGABLE)!=0;
        } catch (Exception e) {

        }
        return false;
    }
}
