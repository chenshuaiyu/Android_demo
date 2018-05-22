package com.example.lenovo.customviewdemo.spider_web_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lenovo.customviewdemo.R;

public class SpiderWebActivity extends AppCompatActivity {

    private SpiderWebView mSpiderWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spider_web);

        mSpiderWebView=findViewById(R.id.spider_web_view);
        mSpiderWebView.setDataNum(8);
        mSpiderWebView.setData(new float[]{43,54,62,34,76,23,55,74});

    }
}
