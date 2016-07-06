package my.microsoft.com.app;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn1,btn2;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(Button) this.findViewById(R.id.btn_show);
        btn2=(Button) this.findViewById(R.id.btn_hide);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        actionBar =getSupportActionBar();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_show:
                actionBar.show();
                break;
            case R.id.btn_hide:
                actionBar.hide();
                break;
        }

    }
}
