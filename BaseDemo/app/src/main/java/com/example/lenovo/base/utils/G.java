package com.example.lenovo.base.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.example.lenovo.base.App;

import static android.os.Build.VERSION.SDK_INT;

/**
 * Coder : chenshuaiyu
 * Time : 2018/7/6 14:07
 */
public class G {

    private static Context mContext;

    public static void getPhoneInfo()throws PackageManager.NameNotFoundException{
        if(null == mContext)
            mContext= App.getInstance();
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);

        //应用版本号和版本名称
        System.out.println("App Version：" + pi.versionCode + "_" + pi.versionCode);
        //Android 版本号
        System.out.println("OS Version：" + Build.VERSION.RELEASE + "_" + SDK_INT);
        //手机制造商
        System.out.println("Vendor: " + Build.MODEL);
        //CPU架构
        System.out.println("CPU ABI: " + Build.CPU_ABI);
    }

}
