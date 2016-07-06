package my.microsoft.com.aidlservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import my.microsoft.com.day04.IMyAidlInterface;

public class AIDLClientActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn01,btn02;
    private Intent intent;
    IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidlclient);
        btn01= (Button) this.findViewById(R.id.AIDLClientActivity_btn01);
        btn02= (Button) this.findViewById(R.id.AIDLClientActivity_btn02);
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        intent=new Intent();
        intent.setAction("com.my.aidlservice");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.AIDLClientActivity_btn01:
                bindService(intent,connection, Context.BIND_AUTO_CREATE);

            break;
            case R.id.AIDLClientActivity_btn02:
                try {
                    //基本类型数据传值
                    int result=iMyAidlInterface.add(1,2);
                    Log.i("aaa","基本类型数据传值,add方法运算结果:"+result);
                    //集合
                    List<String> data=new ArrayList<String>();
                    data.add("郑州");
                    List<String> list =  iMyAidlInterface.getData(data);
                    for(String str:list){
                        Log.i("aaa","服务端返回的数据是:"+str);
                    }
                    Person p = new Person("zhangsan",15);
                    Person person =  iMyAidlInterface.getPerson(p);
                    Log.i("aaa",person.toString());



                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iMyAidlInterface=IMyAidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}
