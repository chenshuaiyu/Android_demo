package com.example.lenovo.cacheimage.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

/**
 * Coder : chenshuaiyu
 * Time : 2018/5/6 16:18
 */
public class MyBitmapUtils {

    private NetCacheUtils mNetCacheUtils;
    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;

    public MyBitmapUtils(Context context) {
        mMemoryCacheUtils=new MemoryCacheUtils();
        mLocalCacheUtils=new LocalCacheUtils(context);
        mNetCacheUtils=new NetCacheUtils(mMemoryCacheUtils,mLocalCacheUtils);
    }

    public void display(ImageView ivPic,String url){
        Bitmap bitmap;

        //内存读取缓存
        bitmap=mMemoryCacheUtils.getBitmapFromMemory(url);
        if(null != bitmap){
            Log.d("数据", "内存读取");
            ivPic.setImageBitmap(bitmap);
            return;
        }else{
            Log.d("数据", "内存 null");
        }

        //本地读取缓存
        bitmap=mLocalCacheUtils.getBitmapFromLocal(url);
        if(null != bitmap){
            Log.d("数据", "本地读取");
            ivPic.setImageBitmap(bitmap);
            return;
        }else{
            Log.d("数据", "本地 null");
        }

        //网络读取缓存
        mNetCacheUtils.getBitmapFromNet(ivPic,url);
        Log.d("数据", "网络读取");

    }


}
