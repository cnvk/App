package my.microsoft.com.day0627;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by czw on 2016/6/27  15:06.
 */
public class B extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        A a=new A();
        a.loadFile("", new A.Callback() {
            @Override
            public void getresult(String str) {
                Log.i("aaa","str="+str);
            }
        });
    }
}
