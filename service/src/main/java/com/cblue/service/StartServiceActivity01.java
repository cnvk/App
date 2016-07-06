package com.cblue.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 启动，停止Service,传值
 */
public class StartServiceActivity01 extends AppCompatActivity implements View.OnClickListener{


    Button btn1,btn2;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service01);

        btn1 = (Button)findViewById(R.id.startservice_activity01_btn01);
        btn2 = (Button)findViewById(R.id.startservice_activity01_btn02);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        intent = new Intent(StartServiceActivity01.this,StartService01.class);
        intent.putExtra("name","zhangsan");

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.startservice_activity01_btn01:
                //启动服务
                startService(intent);
                break;
            case R.id.startservice_activity01_btn02:
                //停止服务
                stopService(intent);
                break;

        }

    }
}
