package my.microsoft.com.day04;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/**
 * Messenger  进行进程之间的传值,day04aidlservice下面的MessengerActivity是客户端,本服务是服务端
 */
public class MessengerService extends Service {
    Messenger messenger=new Messenger(new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Message message=Message.obtain(msg);
            Log.i("aaa","客户端发过来的消息:arg1:"+message.arg1+",arg2:"+message.arg2);
            message.arg1=message.arg1+message.arg2;
            try {
                message.replyTo.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    });

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return messenger.getBinder();
    }
}
