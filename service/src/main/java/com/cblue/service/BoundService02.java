package com.cblue.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by pavel on 2016/6/22.
 */
public class BoundService02 extends Service {

    private MyBinder myBinder = new MyBinder();
    private MediaPlayer mediaPlayer;

    class MyBinder extends Binder{

        public void start(){
            mediaPlayer.start();
        }

        public void stop(){
            mediaPlayer.pause();
            //mediaPlayer.stop();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.beautiful);
        mediaPlayer.setLooping(true);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }
}
