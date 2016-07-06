package com.cblue.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class BoundServiceActivity02 extends AppCompatActivity implements View.OnClickListener {

    Button btn1,btn2;
    Intent intent;
    BoundService02.MyBinder myBinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service02);
        btn1 = (Button)findViewById(R.id.boundservice_activity02_btn01);
        btn2 =(Button)findViewById(R.id.boundservice_activity02_btn02);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        //绑定操作
        intent = new Intent(BoundServiceActivity02.this,BoundService02.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myBinder = (BoundService02.MyBinder)service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.boundservice_activity02_btn01:
                //开始播放
                myBinder.start();
                break;

            case R.id.boundservice_activity02_btn02:
                //停止播放
                myBinder.stop();
                break;
        }
    }
}
