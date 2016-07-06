package com.cblue.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.cblue.customview.R;

/**
 * Created by pavel on 2016/6/30.
 */
public class MyView extends View {

    String txt_value ;
    Drawable image_value;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
       TypedArray typedArray =  context.obtainStyledAttributes(attrs, R.styleable.myview_attrs);
        txt_value=  typedArray.getString(R.styleable.myview_attrs_txt);
        image_value = typedArray.getDrawable(R.styleable.myview_attrs_image);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //图片  文字
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        //需要把drawable对象转成Bitmap
        BitmapDrawable bitmapDrawable = (BitmapDrawable)image_value;
        Bitmap bitmap =  bitmapDrawable.getBitmap();
        canvas.drawBitmap(bitmap,0,0,paint);
       // canvas.drawText(txt_value,0,gette);
        canvas.drawText(txt_value,bitmap.getWidth(),(bitmap.getHeight()+paint.getTextSize())/2,paint);
    }
}
