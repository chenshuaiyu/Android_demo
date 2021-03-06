package com.example.lenovo.customviewdemo.touch_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Coder : chenshuaiyu
 * Time : 2018/3/18 20:44
 */

public class TouchView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private float x;
    private float y;

    private Paint mPaint;
    private Path mPath = new Path();

    private Canvas mCanvas;
    private boolean isDrawing;
    private SurfaceHolder mSurfaceHolder;

    public void initView() {
        initPaint();
        //初始化Holder
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(30);
    }

    public TouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing = false;
    }

    @Override
    public void run() {
        while (isDrawing) {
            try {
                mCanvas = mSurfaceHolder.lockCanvas();

                //绘制触摸路径
                mCanvas.drawPath(mPath, mPaint);

                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
