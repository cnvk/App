package com.cblue.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MessengerClientActivity extends AppCompatActivity {

    Button btn;
    Intent intent;

    //客户端Messenger
    Messenger clientMessenger;
    //发送Messenger
    Messenger sendMessenger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger_client);
        btn = (Button)findViewById(R.id.messenger_activity01_btn01);

        //接收服务端发送消息
        clientMessenger = new Messenger(new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.i("aaa","从服务端得到的信息："+msg.arg1);}
        });
        //连接服务端
        intent = new Intent();
        intent.setAction("com.cblue.processservice.messengerservice");
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i("aaa","连接成功");
                sendMessenger = new Messenger(service);}
            @Override
            public void onServiceDisconnected(ComponentName name) {}
        }, Context.BIND_AUTO_CREATE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = Message.obtain();
                msg.arg1 = 1;msg.arg2 =10;
                msg.replyTo=clientMessenger;
                try {sendMessenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
