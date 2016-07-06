package my.microsoft.com.day02;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Service的生命周期演示
 */
public class StartService03 extends Service {
    public StartService03() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("aaa","onCreate执行了");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("aaa","onStartCommand执行了");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //这个方法在关闭avtivity的时候不会执行,必须进入手机设置,app,手动停止服务才会运行
        Log.i("aaa","onDestroy执行了");
    }
}
