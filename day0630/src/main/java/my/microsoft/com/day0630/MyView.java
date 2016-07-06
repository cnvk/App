package my.microsoft.com.day0630;

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

/**
 * Created by czw on 2016/6/30  09:57.
 */
public class MyView extends View{
    private String txt_value;
    private Drawable img_value;
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.MyView_view);
        txt_value=typedArray.getString(R.styleable.MyView_view_txt);
        img_value=typedArray.getDrawable(R.styleable.MyView_view_image);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        BitmapDrawable bitmapDrawable= (BitmapDrawable) img_value;
        Bitmap bitmap=bitmapDrawable.getBitmap();
        //画图片,从屏幕左上角开始画
        canvas.drawBitmap(bitmap,0,0,paint);
        //画txt,在图片的后面开始画,高度是相对图片居中
        canvas.drawText(txt_value,bitmap.getWidth(),(bitmap.getHeight()+paint.getTextSize())/2,paint);
    }
}
