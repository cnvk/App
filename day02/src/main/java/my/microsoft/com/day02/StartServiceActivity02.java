package my.microsoft.com.day02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//service后台播放音乐
public class StartServiceActivity02 extends AppCompatActivity implements View.OnClickListener{
    private Button btn_play,btn_stop;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service02);
        btn_play= (Button) this.findViewById(R.id.btn_play);
        btn_stop= (Button) this.findViewById(R.id.btn_stop);
        btn_play.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        intent=new Intent(this,StartService02.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_play:
                startService(intent);
            break;
            case R.id.btn_stop:
                stopService(intent);
                break;
        }

    }
}
