package com.cblue.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by pavel on 2016/6/22.
 */
public class BoundService01 extends Service {

    private MyBinder myBinder = new MyBinder();




    class MyBinder extends Binder{

        //写耗时操作
        public void dosomeThing(){
            Log.i("aaa","dosomethTing");
        }

        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if(code==100){
              String name=  data.readString();
                Log.i("aaa","得到Activiy的数据:"+name);
                reply.writeString("zhangsan");
            }
            return super.onTransact(code, data, reply, flags);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("aaa","oncreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
       Log.i("aaa","onbind");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("aaa","onunbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("aaa","ondestroy");
    }
}
