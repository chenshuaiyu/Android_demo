package com.example.lenovo.cacheimage.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Coder : chenshuaiyu
 * Time : 2018/5/6 18:07
 */
public class NetCacheUtils {

    private MemoryCacheUtils mMemoryCacheUtils;
    private LocalCacheUtils mLocalCacheUtils;

    public NetCacheUtils(MemoryCacheUtils memoryCacheUtils, LocalCacheUtils localCacheUtils) {
        mMemoryCacheUtils = memoryCacheUtils;
        mLocalCacheUtils = localCacheUtils;
    }

    public void getBitmapFromNet(ImageView ivPic,String url){
        new BitmapTask().execute(ivPic,url);
    }

    class BitmapTask extends AsyncTask<Object,Void,Bitmap>{

        private ImageView ivPic;
        private String url;

        @Override
        protected Bitmap doInBackground(Object... objects) {
            ivPic= (ImageView) objects[0];
            url= (String) objects[1];

            return downLoadBitmap(url);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ivPic.setImageBitmap(bitmap);
        }
    }

    private Bitmap downLoadBitmap(String url){
        HttpURLConnection conn=null;
        try {
            conn= (HttpURLConnection) new URL(url).openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");

            int responseCode=conn.getResponseCode();
            if(responseCode==200){
                BitmapFactory.Options options=new BitmapFactory.Options();
                options.inSampleSize=2;
                options.inPreferredConfig=Bitmap.Config.ARGB_4444;
                Bitmap bitmap=BitmapFactory.decodeStream(conn.getInputStream());


                mLocalCacheUtils.setBitmapToLocal(url, bitmap);
                mMemoryCacheUtils.setBitmapFromMemory(url, bitmap);

                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            conn.disconnect();
        }

        return null;
    }


}
