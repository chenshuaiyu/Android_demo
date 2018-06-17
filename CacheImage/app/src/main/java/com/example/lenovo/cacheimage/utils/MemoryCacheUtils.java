package com.example.lenovo.cacheimage.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import java.lang.ref.SoftReference;


/**
 * Coder : chenshuaiyu
 * Time : 2018/5/6 16:19
 *
 * 通过 HashMap<String,Bitmap> 的方式保存图片，但是强引用对象容易造成内存溢出，可以使用SoftReference软引用对象
 * 使用 HashMap<String,SoftReference<Bitmap> ,SoftReference为弱引用对象（ GC 垃圾回收会自动回软引用对象），但在2.3之后，系统会优先考虑回收软引用对象，官方推荐使用LruCache
 * 使用 LruCache<String,Bitmap> （latest recently use 最少最近使用算法）会将内存控制在一定的大小内，超过最大值会自动回收，最大值由开发者自己定
 *
 */
public class MemoryCacheUtils {

    private LruCache<String,Bitmap> mMemoryCache;

    public MemoryCacheUtils() {
        long maxMemory=Runtime.getRuntime().maxMemory()/8;

        mMemoryCache=new LruCache<String,Bitmap>((int) maxMemory){
            //用于计算每个条目的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
//                1.强引用方法
//                Bitmap bitmap = mMemoryCache.get(url);
//                2.弱引用方法
//                SoftReference<Bitmap> bitmapSoftReference = mMemoryCache.get(url);
//                if (bitmapSoftReference != null) {
//                    Bitmap bitmap = bitmapSoftReference.get();
//                    return bitmap;
//                }

                return value.getByteCount();
            }
        };
    }

    public Bitmap getBitmapFromMemory(String url){
        return mMemoryCache.get(url);
    }

    public void setBitmapFromMemory(String url,Bitmap bitmap){
//        1.强引用方法
//        mMemoryCache.put(url, bitmap);
//        2.弱引用方法
//        mMemoryCache.put(url, new SoftReference<>(bitmap));

        mMemoryCache.put(url, bitmap);
    }



}
