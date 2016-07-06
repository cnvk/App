package com.cblue.tuya;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by pavel on 2016/6/30.
 */
public class TuYaView extends View {

    //画板
    private Canvas canvas;

    //纸
    private Bitmap bitmap;

    //笔
    private Paint paint;
    //路径
    private Path path;

    //临时保存X Y
    private float mx,my;

    public TuYaView(Context context,int screenWith,int screenHeight) {
        super(context);
        //我需要一个画板  还需要纸，还需要笔

        bitmap = Bitmap.createBitmap(screenWith,screenHeight, Bitmap.Config.ARGB_8888);

        canvas = new Canvas(bitmap);

        paint = new Paint();
        paint.setAntiAlias(true);//设置平滑效果
        paint.setStyle(Paint.Style.STROKE);//画实线
        paint.setStrokeWidth(5);//线的宽度
    }

    public TuYaView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TuYaView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //单点触控
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("aaa","onTouchEvent");
       int action =  event.getAction();
        Log.i("aaa","event.getPointerCount()="+event.getPointerCount()+"");

        float x = event.getX();
        float y =  event.getY();

        Log.i("aaa","x="+x+";y="+y);
        switch (action){
            case MotionEvent.ACTION_DOWN:
                // Log.i("aaa","down");
                path = new Path();
                path.moveTo(x,y);
                mx = x;
                my = y;
               // invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
               // Log.i("aaa","move");
                //得到滑动距离
                float dx = Math.abs(x-mx);
                float dy = Math.abs(y-my);
                //当手指移动的距离大于4的时候，才划线
                if(dx>4||dy>4){
                    path.lineTo(x,y);
                    canvas.drawPath(path,paint);
                }
                mx = x;
                my = y;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,0,0,paint);
    }
}
