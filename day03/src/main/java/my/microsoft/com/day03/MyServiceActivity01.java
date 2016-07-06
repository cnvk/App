package my.microsoft.com.day03;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * BoundService播放音乐
 */
public class MyServiceActivity01 extends AppCompatActivity implements View.OnClickListener{
    private Button btn1,btn2;
    private Intent intent;
    private MyService01.MyBinder myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service01);
        btn1= (Button) this.findViewById(R.id.MyServiceActivity01_btn1);
        btn2= (Button) this.findViewById(R.id.MyServiceActivity01_btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        intent=new Intent(this,MyService01.class);
       bindService(intent, new ServiceConnection() {
           @Override
           public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
               myBinder= (MyService01.MyBinder) iBinder;

           }

           @Override
           public void onServiceDisconnected(ComponentName componentName) {

           }
       },BIND_AUTO_CREATE);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.MyServiceActivity01_btn1:
                myBinder.start();

                break;
            case R.id.MyServiceActivity01_btn2:
                myBinder.stop();

                break;
        }
    }
}
