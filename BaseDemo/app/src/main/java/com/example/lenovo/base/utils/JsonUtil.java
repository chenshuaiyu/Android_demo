package com.example.lenovo.base.utils;

import android.text.TextUtils;

import com.google.gson.Gson;

/**
 * Coder : chenshuaiyu
 * Time : 2018/4/6 20:56
 */
public class JsonUtil {

    private static Gson mGson;

    public static <T> T parsrJson(String json, Class<T> tClass) {
        if (null == mGson) {
            mGson = new Gson();
        }
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        return mGson.fromJson(json, tClass);

    }

}
