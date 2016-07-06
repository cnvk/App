package my.microsoft.com.day04;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AIDLService extends Service {
     class AIDLInterface extends IMyAidlInterface.Stub{


         @Override
         public int add(int a, int b) throws RemoteException {
             return a+b;
         }

         @Override
         public List<String> getData(List<String> list) throws RemoteException {
             for(String str:list){
                 Log.i("aaa","客户端发送的数据:"+str);
             }
             List<String> data = new ArrayList<String>();
             data.add("北京");
             return data;
         }
         @Override
         public Person getPerson(Person p) throws RemoteException {
             Log.i("aaa","客户端发送的person："+p.toString());
             Person person = new Person("lisi",20);
             return person;
         }

     }

    @Override
    public IBinder onBind(Intent intent) {

        return new AIDLInterface();
    }
}
