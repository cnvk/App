package com.cblue.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by pavel on 2016/6/22.
 */
public class IntentService01 extends IntentService {

    //IntentService的子类需要调用父类的构造函数，函数的参数是IntentService的子类的名称
    public IntentService01() {
        super("IntentService01");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
       //耗时操作
        for(int i=0;i<10;i++){
            try {
                Thread.sleep(10*1000);
                Log.i("aaa","id="+Thread.currentThread().getId()+"--i="+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
