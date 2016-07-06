package com.cblue.processservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class AIDLServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidlservice);

        Intent intent = new Intent();
        //隐式Intent
        intent.setAction("com.cblue.processservice.aidlservice");
        startService(intent);
        Log.i("aaa","AIDL服务启动");
    }
}
