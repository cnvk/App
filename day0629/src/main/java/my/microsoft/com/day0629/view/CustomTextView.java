package my.microsoft.com.day0629.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by czw on 2016/6/29  17:13.
 */
public class CustomTextView extends TextView{
    private String value;

    //当用代码创建自定义view的时候,会调用这个方法
    public CustomTextView(Context context) {
        super(context);
    }

    //当用布局文件创建自定义view的时候,会调用这个方法
    //AttributeSet attrs这个是布局文件中view的集合
    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //getAttributeValue(布局文件的工作空间,控件的属性名)
        //value得到的就是text的值"AAAA"
        value=attrs.getAttributeValue("http://schemas.android.com/apk/res/android","text");
    }

    //当用布局文件，并且布局文件中包含style属性的时候,会调用这个方法
    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //这个方法就是画出自定义view的属性
    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(20);
        paint.setFakeBoldText(true);//文字加粗
        //drawText(value就是控件上的文字,开始画的起始位置X轴,Y轴,paint)
        canvas.drawText(value,0,getTextSize(),paint);
    }
}
