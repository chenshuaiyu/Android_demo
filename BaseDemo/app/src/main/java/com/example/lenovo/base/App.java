package com.example.lenovo.base;

import android.app.Application;
import android.content.Context;

/**
 * Coder : chenshuaiyu
 * Time : 2018/7/2 16:42
 * 注意 : 在Manifest文件中的application添加name属性
 */
public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
    }

    public static Context getInstance(){
        return mContext;
    }

}
