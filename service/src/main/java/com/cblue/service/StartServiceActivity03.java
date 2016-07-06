package com.cblue.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/*
  使用Service播放音乐
 */
public class StartServiceActivity03 extends AppCompatActivity implements View.OnClickListener {

    Button btn1,btn2;
    Intent intent ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_sevice03);

        btn1 =(Button)findViewById(R.id.startservice_activity03_btn01);
        btn2 = (Button)findViewById(R.id.startservice_activity03_btn02);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        intent = new Intent(StartServiceActivity03.this,StartService03.class);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startservice_activity03_btn01:
           //播放音乐
             startService(intent);
                break;
            case R.id.startservice_activity03_btn02:
             //停止音乐
                stopService(intent);
                break;
        }

    }
}
