package com.example.lenovo.base.utils;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.example.lenovo.base.App;

/**
 * Coder : chenshuaiyu
 * Time : 2018/7/2 16:55
 */
public class T {

    public static boolean iShow=true;

    public T() {
    }

    public void showShort(CharSequence message){
        if (iShow)
            Toast.makeText(App.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    public void showShort(@StringRes int message){
        if (iShow)
            Toast.makeText(App.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    public void showLong(CharSequence message){
        if (iShow)
            Toast.makeText(App.getInstance(), message, Toast.LENGTH_LONG).show();
    }

    public void showLong(@StringRes int message){
        if (iShow)
            Toast.makeText(App.getInstance(), message, Toast.LENGTH_LONG).show();
    }

    public void show(CharSequence message,int duration){
        if (iShow)
            Toast.makeText(App.getInstance(), message, duration).show();
    }

    public void show(@StringRes int message,int duration){
        if (iShow)
            Toast.makeText(App.getInstance(), message, duration).show();
    }



}
