package my.microsoft.com.day04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AIDLServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidlservice);
        Intent intent=new Intent();
        intent.setAction("com.my.aidlservice");
        startService(intent);
    }
}
