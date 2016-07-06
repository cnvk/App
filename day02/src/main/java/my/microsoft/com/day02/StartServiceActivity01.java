package my.microsoft.com.day02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//演示service执行后台的耗时操作
public class StartServiceActivity01 extends AppCompatActivity implements View.OnClickListener{
    private Button btn01,btn02;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service01);
        btn01= (Button) this.findViewById(R.id.btn01);
        btn02= (Button) this.findViewById(R.id.btn02);
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        intent=new Intent(this,StartService01.class);
        intent.putExtra("name","张三");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn01:
                startService(intent);
                break;
            case R.id.btn02:
                stopService(intent);
                break;
        }
    }
}
