package com.example.lenovo.clockview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Calendar;

/**
 * Coder : chenshuaiyu
 * Time : 2018/3/18 13:39
 */

public class ClockView extends SurfaceView implements SurfaceHolder.Callback,Runnable{

    private float mWidth;
    private float mHeight;
    private Paint mDialPaint;
    private Paint mDialBoldPaint;
    private Paint mNumPaint;
    private Paint mPointerPaint;
    private float r;//表盘半径

    private SurfaceHolder mSurfaceHolder;
    private Canvas mCanvas;
    private boolean isDrawing;

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initPaint() {
        mDialPaint=new Paint();
        mDialPaint.setColor(Color.BLACK);
        mDialPaint.setStyle(Paint.Style.STROKE);
        mDialPaint.setStrokeWidth(10);

        mDialBoldPaint = new Paint();
        mDialBoldPaint.setColor(Color.BLACK);
        mDialBoldPaint.setStyle(Paint.Style.STROKE);
        mDialBoldPaint.setStrokeWidth(15);

        mNumPaint=new Paint();
        mNumPaint.setColor(Color.BLACK);
        mNumPaint.setStyle(Paint.Style.STROKE);
        mNumPaint.setStrokeWidth(5);
        mNumPaint.setTextSize(50);

        mPointerPaint=new Paint();
        mPointerPaint.setColor(Color.BLACK);
        mPointerPaint.setStyle(Paint.Style.STROKE);
        mPointerPaint.setStrokeWidth(10);
    }

    private void initView(){
        initPaint();
        //获得SurfaceHolder对象
        mSurfaceHolder=getHolder();
        //添加SurfaceHolder的回调方法
        mSurfaceHolder.addCallback(this);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;
        //设置半径为宽高最小值的 40%
        r= (float) (0.4 * (h > w ? w :h));
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isDrawing=true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing=false;
    }

    @Override
    public void run() {
        while(isDrawing){
            try {
                mCanvas=mSurfaceHolder.lockCanvas();

                mCanvas.drawColor(Color.WHITE);

                drawClock(mCanvas);

                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void drawClock(Canvas canvas){
        if(canvas==null)
            return;
        drawDial(canvas);
        drawPointer(canvas);
    }

    private void drawPointer(Canvas canvas) {

        canvas.rotate(-90);

        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR);
        int minute=calendar.get(Calendar.MINUTE);
        int second=calendar.get(Calendar.SECOND);

        double x,y;

        mPointerPaint.setStrokeWidth(13);
        x = Math.cos(hour * 30 * Math.PI / 180);
        y = Math.sin(hour * 30 * Math.PI / 180);
        canvas.drawLine(0, 0, (float) (r * 0.5 * x), (float) (r * 0.5 * y), mPointerPaint);

        mPointerPaint.setStrokeWidth(10);
        x = Math.cos(minute * 6 * Math.PI / 180);
        y = Math.sin(minute * 6 * Math.PI / 180);
        canvas.drawLine(0, 0, (float) (r * 0.65 * x), (float) (r * 0.65 * y), mPointerPaint);

        mPointerPaint.setStrokeWidth(7);
        x = Math.cos(second * 6 * Math.PI / 180);
        y = Math.sin(second * 6 * Math.PI / 180);
        canvas.drawLine(0, 0, (float) (r * 0.75 * x), (float) (r * 0.75 * y), mPointerPaint);

    }

    private void drawDial(Canvas canvas){
        canvas.translate(mWidth/2,mHeight/2);

        canvas.drawCircle(0,0,r,mDialPaint);
        canvas.drawPoint(0,0,mDialBoldPaint);

        Path pathDial=new Path();
        double x,y;

        for (int i = 0; i < 360; i =i + 6) {
            x = Math.cos(i * Math.PI / 180);
            y = Math.sin(i * Math.PI / 180);
            if (i % 30 != 0) {
                pathDial.reset();
                pathDial.moveTo((float) (r * x), (float) (r * y));
                pathDial.lineTo((float) ((r - r * 0.1) * x), (float) ((r - r * 0.1) * y));
                canvas.drawPath(pathDial, mDialPaint);
            } else {
                pathDial.reset();
                pathDial.moveTo((float) (r * x), (float) (r * y));
                pathDial.lineTo((float) ((r - r * 0.15) * x), (float) ((r - r * 0.15) * y));
                canvas.drawPath(pathDial, mDialBoldPaint);
                int temp = (i + 30) / 30 - 1;
                if (temp != 0)
                    canvas.drawText(temp + "", (float) ((r - r * 0.2) * Math.cos((i - 90) * Math.PI / 180) - r * 0.04), (float) ((r - 70) * Math.sin((i - 90) * Math.PI / 180) + r * 0.04), mNumPaint);
                else
                    canvas.drawText("12", (float) ((r - 70) * Math.cos((i - 90) * Math.PI / 180) - r * 0.04), (float) ((r - 70) * Math.sin((i - 90) * Math.PI / 180) + r * 0.04), mNumPaint);
            }
        }

    }

}
