package my.microsoft.com.day02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Service的生命周期演示
 */
public class StartServiceActivity03 extends AppCompatActivity implements View.OnClickListener{
    private Button btn1;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service03);
        btn1= (Button) this.findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        intent=new Intent(this,StartService03.class);
    }

    @Override
    public void onClick(View view) {
        startService(intent);
    }
}
