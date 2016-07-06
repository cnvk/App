package my.microsoft.com.day0629.viewgroup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import my.microsoft.com.day0629.R;

public class CustomViewGroupActivity extends AppCompatActivity implements View.OnClickListener{
    private CustomViewGroup customViewGroup;
    private Button btn1,btn2;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_group);
        customViewGroup= (CustomViewGroup) this.findViewById(R.id.view);
        btn1= (Button) customViewGroup.findViewById(R.id.view_btn1);
        btn2= (Button) customViewGroup.findViewById(R.id.view_btn2);
        tv1= (TextView) customViewGroup.findViewById(R.id.view_tv);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.view_btn1:
                tv1.setText("按钮1点击了");
                break;
            case R.id.view_btn2:
                tv1.setText("按钮2点击了");
                break;

        }

    }
}
