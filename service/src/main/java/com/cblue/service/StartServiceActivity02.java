package com.cblue.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 启动Service的生命周期
 */
public class StartServiceActivity02 extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service02);

        btn = (Button)findViewById(R.id.startservice_activity02_btn01);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartServiceActivity02.this,StartService02.class);
                startService(intent);
            }
        });
    }
}
