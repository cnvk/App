package com.cblue.customviewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.cblue.customview.R;

/**
 * Created by pavel on 2016/6/29.
 */
public class CustomViewGroup extends LinearLayout {


    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        //得到布局
        LayoutInflater.from(context).inflate(R.layout.custom_view_group,this,true);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
