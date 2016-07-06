package com.cblue.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class IntentServiceActivity02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service02);

        Intent intent = new Intent(IntentServiceActivity02.this,IntentService02.class);
        intent.putExtra("urlStr","https://www.baidu.com/img/bd_logo1.png");
        startService(intent);
    }
}
