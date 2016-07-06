package my.microsoft.com.day0629.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import my.microsoft.com.day0629.R;

/**
 * Created by czw on 2016/6/29  18:40.
 */
public class CustomViewGroup extends LinearLayout{
    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
       //得到布局
        LayoutInflater.from(context).inflate(R.layout.viewgroup,this,true);

    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
