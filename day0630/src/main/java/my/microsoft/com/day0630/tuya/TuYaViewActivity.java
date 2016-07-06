package my.microsoft.com.day0630.tuya;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import my.microsoft.com.day0630.R;

public class TuYaViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //得到屏幕的宽高
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        TuYaView tuYaView=new TuYaView(TuYaViewActivity.this,displayMetrics.widthPixels,displayMetrics.heightPixels);
        //加载涂鸦view
        setContentView(tuYaView);

        //setContentView(R.layout.activity_tu_ya_view);
    }
}
