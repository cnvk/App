package com.cblue.customviewgroup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cblue.customview.R;

public class CustomViewGroupActivity extends AppCompatActivity implements View.OnClickListener{

    CustomViewGroup customViewGroup;
    Button btn1,btn2;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_group);
        customViewGroup = (CustomViewGroup)findViewById(R.id.customviewgroup);
        btn1 = (Button) customViewGroup.findViewById(R.id.custom_view_group_btn1);
        btn2 = (Button)customViewGroup.findViewById(R.id.custom_view_group_btn2);
        tv = (TextView)customViewGroup.findViewById(R.id.custom_view_group_tv1);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.custom_view_group_btn1:
               tv.setText("btn1");
                break;

            case R.id.custom_view_group_btn2:
                tv.setText("btn2");
                break;
        }

    }
}
