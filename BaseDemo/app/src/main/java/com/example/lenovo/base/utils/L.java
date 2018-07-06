package com.example.lenovo.base.utils;

import android.util.Log;

/**
 * Coder : chenshuaiyu
 * Time : 2018/7/2 17:05
 */
public class L {
    public static final String TAG = "Log";

    private static boolean isDebug = true;

    public L() {
    }

    //默认TAG
    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void w(String msg) {
        if (isDebug)
            Log.w(TAG, msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    //自定义TAG
    public static void v(String tag,String msg) {
        if (isDebug)
            Log.v(tag, msg);
    }

    public static void d(String tag,String msg) {
        if (isDebug)
            Log.d(tag, msg);
    }

    public static void i(String tag,String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void w(String tag,String msg) {
        if (isDebug)
            Log.w(tag, msg);
    }

    public static void e(String tag,String msg) {
        if (isDebug)
            Log.e(tag, msg);
    }



}
