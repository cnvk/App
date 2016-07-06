package com.cblue.processservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by pavel on 2016/6/23.
 */
public class MessengerService extends Service {

    //创建Messenger对象
    Messenger messenger = new Messenger(new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //打印消息
            Log.i("aaa",msg.arg1+":"+msg.arg2);
             //通过客户端传递过来的消息，创建服务端的消息对象
            Message message = Message.obtain(msg);
            //把服务端的消息传递给客户端
            message.arg1 = msg.arg1+msg.arg2;
            try {
                Messenger mes = message.replyTo;
                mes.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    });
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
