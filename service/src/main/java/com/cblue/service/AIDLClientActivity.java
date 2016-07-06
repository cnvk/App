package com.cblue.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cblue.processservice.IMyAidlInterface;
import com.cblue.processservice.Person;

import java.util.ArrayList;
import java.util.List;

public class AIDLClientActivity extends AppCompatActivity implements View.OnClickListener {


    Button btn1,btn2;
    Intent intent ;
    IMyAidlInterface myAidlInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidlclient);

        btn1 = (Button)findViewById(R.id.aidl_client_activtiy_btn01);
        btn2 = (Button)findViewById(R.id.aidl_client_activtiy_btn02);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        intent = new Intent();
        intent.setAction("com.cblue.processservice.aidlservice");


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.aidl_client_activtiy_btn01:
                //绑定服务
                bindService(intent,connection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.aidl_client_activtiy_btn02:
               //调用aidl方法
                try {
                   int result =  myAidlInterface.add(1,2);
                    Log.i("aaa","result="+result);

                    List<String> data = new ArrayList<String>();
                    data.add("郑州");
                   List<String> list =  myAidlInterface.getData(data);
                    for(String str:list){
                        Log.i("aaa","服务端返回的数据是:"+str);
                    }
                    Person p = new Person("zhangsan",15);
                     Person person =  myAidlInterface.getPerson(p);
                    Log.i("aaa",person.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }

    }
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAidlInterface  = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
