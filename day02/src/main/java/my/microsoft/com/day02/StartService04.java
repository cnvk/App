package my.microsoft.com.day02;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * 演示service执行耗时操作,ANR错误
 */
public class StartService04 extends Service {
    public StartService04() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //执行耗时操作
        for (int i=0;i<10;i++){
            try {
                Thread.sleep(7*1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i("aaa","i="+i);
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
