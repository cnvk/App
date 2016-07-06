package my.microsoft.com.day03;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

/**
 * BoundService播放音乐
 */

public class MyService01 extends Service {
    private MediaPlayer mediaPlayer;
    private MyBinder myBinder=new MyBinder();

    class MyBinder extends Binder {
        public void start(){
            mediaPlayer.start();
        }
        public void stop(){
            //这个是停止,点完停止,再点播放就没反应了
            //mediaPlayer.stop();
            //这个是暂停,再点播放还会继续播放
            mediaPlayer.pause();
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.hello);
        mediaPlayer.setLooping(true);
    }

    @Override
    public IBinder onBind(Intent intent) {

        return myBinder;
    }
}
