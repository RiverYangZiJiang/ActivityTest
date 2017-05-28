package com.example.yangzijiang.activitytest;

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
}
