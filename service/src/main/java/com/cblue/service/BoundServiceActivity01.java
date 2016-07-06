package com.cblue.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class BoundServiceActivity01 extends AppCompatActivity implements View.OnClickListener {

    Button btn1,btn2,btn3;
    Intent intent ;
    BoundService01.MyBinder myBinder;
    boolean isConnect = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service01);
        btn1 = (Button)findViewById(R.id.boundservice_activity01_btn01);
        btn2 = (Button)findViewById(R.id.boundservice_activity01_btn02);
        btn3 = (Button)findViewById(R.id.boundservice_activity01_btn03);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        intent = new Intent(BoundServiceActivity01.this,BoundService01.class);
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.boundservice_activity01_btn01:
              //绑定Service
              bindService(intent,connection,Context.BIND_AUTO_CREATE);
              isConnect = true;
              break;
          case R.id.boundservice_activity01_btn02:
              //解绑service
              if(isConnect) {
                  unbindService(connection);
                  isConnect = false;
              }
              break;
          case R.id.boundservice_activity01_btn03:
              //传值
              Parcel data = Parcel.obtain();
              data.writeString("name");
              Parcel reply  = Parcel.obtain();
              try {
                  myBinder.transact(100,data,reply,1);
                  String value = reply.readString();
                  Log.i("aaa","得到Service的数据："+value);

              } catch (RemoteException e) {
                  e.printStackTrace();
              }
              break;
      }
    }

    ServiceConnection connection  = new ServiceConnection() {
        //当我们绑定服务的时候，Activity和Service之间产生一个连接
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("aaa","onServiceConnected");
            myBinder = (BoundService01.MyBinder)service;
            myBinder.dosomeThing();;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //绑定Service异常销毁时（内存不足时），才执行这个方法
            Log.i("aaa","onServiceDisconnected");
        }
    };
}
