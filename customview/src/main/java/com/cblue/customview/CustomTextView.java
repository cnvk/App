package com.cblue.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * 把文字变成红色
 * Created by pavel on 2016/6/29.
 */
public class CustomTextView extends TextView {

    private String value ;
    //当你用代码来创建控件的时候
   // CustomTextView customTextView = new CustomTextView();
    public CustomTextView(Context context) {
        super(context);
    }

    //当我们使用布局文件创建控件的时候
    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("aaa","CustomTextView第二个构造函数");
        value = attrs.getAttributeValue("http://schemas.android.com/apk/res/android","text");
        Log.i("aaa","value="+value);
    }

    //当我们使用布局文件，并且布局文件中包含style属性的时候
    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
       // super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
       // paint.setTextSize(20);
        paint.setFakeBoldText(true);
        canvas.drawText(value,0,getTextSize(),paint);
    }
}
