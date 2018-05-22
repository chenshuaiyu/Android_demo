package com.example.lenovo.customviewdemo.spider_web_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Coder : chenshuaiyu
 * Time : 2018/3/10 9:07
 */

public class SpiderWebView extends View {

    private int mCount;
    private float centerX;
    private float centerY;
    private Paint mWebPaint;
    private float radius;

    private float[] mData = {};

    private void init() {
        mWebPaint = new Paint();
        mWebPaint.setColor(Color.GRAY);
        mWebPaint.setStyle(Paint.Style.STROKE);
        mWebPaint.setStrokeWidth(3f);
        radius = Math.min(centerX, centerY);
    }


    public SpiderWebView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SpiderWebView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setDataNum(int count){
        mCount=count;
    }

    public void setData(float[] data){
        mData=data;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        centerX = MeasureSpec.getSize(widthMeasureSpec) / 2;
        centerY = MeasureSpec.getSize(heightMeasureSpec) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(centerX, centerY);
        //绘制多边形
        drawPolygon(canvas);
        //
        drawLines(canvas);
        //
        drawRegion(canvas);
    }

    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        float max = max(mData);
        float r = radius / mCount * (mCount - 1);
        float x = 0;
        float y = 0;
        double angle = (double) (Math.PI * 2 / mCount);
        float length = 0;
        mWebPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mWebPaint.setColor(Color.BLUE);
        mWebPaint.setAlpha(127);

        for (int i = 0; i < mCount; i++) {
            length = mData[i] * r / max;
            x = (float) (length * Math.cos(angle * i));
            y = (float) (length * Math.sin(angle * i));
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
            canvas.drawCircle(x, y, 15, mWebPaint);
        }
        path.close();
        canvas.drawPath(path, mWebPaint);
    }

    private float max(float[] data) {
        float max = data[0];
        for (int i = 1; i < mCount; i++) {
            max = Math.max(max, data[i]);
        }
        return max;
    }

    private void drawLines(Canvas canvas) {
        Path path = new Path();
        float r = radius / mCount * (mCount - 1);
        float x = 0;
        float y = 0;
        double angle = (double) (Math.PI * 2 / mCount);

        for (int i = 0; i < mCount; i++) {
            x = (float) (r * Math.cos(angle * i));
            y = (float) (r * Math.sin(angle * i));
            path.lineTo(x, y);
            canvas.drawPath(path, mWebPaint);
            path.moveTo(0, 0);
        }
    }

    private void drawPolygon(Canvas canvas) {
        init();
        Path path = new Path();
        float r = radius / mCount;
        double angle = (double) (Math.PI * 2 / mCount);

        float x = 0;
        float y = 0;

        for (int j = 1; j < mCount; j++) {
            for (int i = 0; i < mCount; i++) {
                if (i == 0) {
                    x = r * j;
                    y = 0;
                    path.moveTo(x, y);
                } else {
                    x = (float) (r * j * Math.cos(angle * i));
                    y = (float) (r * j * Math.sin(angle * i));
                    path.lineTo(x, y);
                }
            }
            path.close();
        }
        canvas.drawPath(path, mWebPaint);
    }
}
