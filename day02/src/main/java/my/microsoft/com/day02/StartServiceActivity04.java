package my.microsoft.com.day02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 演示service执行耗时操作,ANR错误
 */
public class StartServiceActivity04 extends AppCompatActivity {
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service04);
        btn1= (Button) this.findViewById(R.id.StartServiceActivity04_btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StartServiceActivity04.this,StartService04.class);
                startService(intent);
            }
        });
    }
}
