package com.example.lenovo.cacheimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.lenovo.cacheimage.utils.LocalCacheUtils;
import com.example.lenovo.cacheimage.utils.MemoryCacheUtils;
import com.example.lenovo.cacheimage.utils.MyBitmapUtils;
import com.example.lenovo.cacheimage.utils.NetCacheUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private String url="https://pic1.zhimg.com//v2-09d4b2c0bfcd793f055add21696a4904.jpg";

    private ImageView mImageView;
    private MyBitmapUtils mMyBitmapUtils;
    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;
    private NetCacheUtils mNetCacheUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView=findViewById(R.id.image_view);

        mMyBitmapUtils=new MyBitmapUtils(this);

        mImageView.setImageResource(R.drawable.ic_launcher_background);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyBitmapUtils.display(mImageView, url);
            }
        });

    }

}
