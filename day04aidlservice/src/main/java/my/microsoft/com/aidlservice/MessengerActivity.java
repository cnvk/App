package my.microsoft.com.aidlservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Messenger  进行进程之间的传值,day04--MessengerService是服务端,本页面是客户端
 */
public class MessengerActivity extends AppCompatActivity {
    private Button btn01;
    private Messenger clientMessenger;
    private Messenger sendMessenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        btn01= (Button) this.findViewById(R.id.MessengerActivity_btn01);
        clientMessenger=new Messenger(new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.i("aaa","服务端发来的信息:arg1:"+msg.arg1);
            }
        });
        //连接服务端
        Intent intent=new Intent();
        intent.setAction("com.day04.messengerservice");
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.i("aaa","客户端连服务端成功");
                sendMessenger=new Messenger(iBinder);

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        }, Context.BIND_AUTO_CREATE);
        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg=Message.obtain();
                msg.arg1=1;
                msg.arg2=10;
                msg.replyTo=clientMessenger;
                try {
                    sendMessenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
