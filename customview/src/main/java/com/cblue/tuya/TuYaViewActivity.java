package com.cblue.tuya;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

public class TuYaViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //得到屏幕的宽高
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        TuYaView tuYaView = new TuYaView(TuYaViewActivity.this,displayMetrics.widthPixels,displayMetrics.heightPixels);
        setContentView(tuYaView);
    }
}
