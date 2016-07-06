package my.microsoft.com.day0630.tuya;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by czw on 2016/6/30  14:06.
 */
public class TuYaView extends View{
    //画板
    private Canvas canvas;
    private Bitmap bitmap;
    private Paint paint;
    private Path path;
    private float mx,my;

    public TuYaView(Context context,int screenWith,int screenHeight) {
        super(context);
        bitmap=Bitmap.createBitmap(screenWith,screenHeight,Bitmap.Config.ARGB_8888);
        canvas=new Canvas(bitmap);
    }

    public TuYaView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public TuYaView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
