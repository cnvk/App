package com.cblue.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 点击按钮下载
 * 下载开始,Activity启动一个服务
 * onStartCommand的方法中耗时操作（下载，保存SD卡）
 *
 * Notification结合
 *    1.下载文件的大小100
 *    2.当前已经下载的大小30
 */

public class StartServiceActivity05 extends AppCompatActivity {

    Button btn;
    String urlStr = "http://img11.360buyimg.com/da/jfs/t2728/19/2478514029/102689/e2957559/576760adNe19d9278.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service05);
        btn = (Button)findViewById(R.id.startservice_activity05_btn01);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(StartServiceActivity05.this,StartService05.class);
                intent.putExtra("urlStr",urlStr);
                startService(intent);
            }
        });
    }
}
