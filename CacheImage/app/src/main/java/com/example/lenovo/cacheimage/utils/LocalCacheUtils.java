package com.example.lenovo.cacheimage.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Coder : chenshuaiyu
 * Time : 2018/5/6 16:20
 * 初次通过网络加载图片时，将图片保存到本地 SD 卡
 * 使用 MD5 加密图片的网络地址，作为图片名称来保存
 */
public class LocalCacheUtils {

    public static String CACHE_PATH="";

    private Context mContext;
    private DiskLruCache mDiskLruCache;

    public LocalCacheUtils(Context context) {
        mContext = context;
        if(TextUtils.isEmpty(CACHE_PATH)){
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
                    || !Environment.isExternalStorageRemovable()){
                // 文件地址 sdcard/Android/data/<application package>/cache
                CACHE_PATH=mContext.getExternalCacheDir().getPath();
            }else{
                // 文件地址 /data/data/<application package>/cache
                CACHE_PATH=mContext.getCacheDir().getPath();
            }
        }
    }



    /**
     * 从本地读取图片
     * @param url
     * @return
     */
    public Bitmap getBitmapFromLocal(String url){
        Bitmap bitmap = null;
        //把图片的 url 进行加密作为文件名
        String fileName=null;
        try {
            fileName=MD5Helper.toMD5(url);
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(fileName);
            if(null != snapshot){
                InputStream i=snapshot.getInputStream(0);
                bitmap= BitmapFactory.decodeStream(i);
            }

            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void setBitmapToLocal(String url,Bitmap bitmap) {

        String filePath = CACHE_PATH;
        String fileName;
        try {
            fileName = MD5Helper.toMD5(url);
            filePath = filePath + File.separator + fileName;
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }

            mDiskLruCache = DiskLruCache.open(file, getAppVersion(), 1, 10 * 1024 * 1024);

            DiskLruCache.Editor editor = mDiskLruCache.edit(fileName);
            if (null != editor) {
                OutputStream o = editor.newOutputStream(0);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, o);
                Log.d("数据", "存到本地");
                editor.commit();
            }
            mDiskLruCache.flush();

        } catch (Exception e) {
            Log.d("数据", "setBitmapToLocal: "+e.toString());
            e.printStackTrace();
        }
    }

    private int getAppVersion(){
        try {
            return mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }


}
