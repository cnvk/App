package com.cblue.image.cache3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cblue.mutilmedia.R;

public class CacheActivity_Three extends AppCompatActivity implements View.OnClickListener{

    Button btn1,btn2;
    ImageView iv1,iv2;
    CacheManager cacheManager;
    String urlStr = "http://img12.360buyimg.com/n1/s130x130_jfs/t2338/121/2876050819/63934/4bb7d2d2/571ddb8fN706d2070.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache_activity__three);
        btn1 = (Button)findViewById(R.id.cacheactivity_three_btn1);
        btn2= (Button)findViewById(R.id.cacheactivity_three_btn2);
        iv1 = (ImageView)findViewById(R.id.cacheactivity_three_iv1);
        iv2 = (ImageView)findViewById(R.id.cacheactivity_three_iv2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        cacheManager = new CacheManager();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cacheactivity_three_btn1:
                //第一次测量下载
                cacheManager.getCache(urlStr,iv1);
                break;
            case R.id.cacheactivity_three_btn2:
                //测量缓存
                cacheManager.getCache(urlStr,iv2);
                break;


        }
    }
}
