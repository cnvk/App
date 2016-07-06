package com.cblue.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by pavel on 2016/6/21.
 */
public class StartService04 extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //执行耗时操作
        for(int i=0;i<10;i++){
            try {
                Thread.sleep(7 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i("aaa","i="+i);
        }

        return super.onStartCommand(intent, flags, startId);
    }
}
