package my.microsoft.com.app;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class ActionBarActivity03 extends AppCompatActivity {
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar03);
        actionBar=getSupportActionBar();
        //设置标题能够显示
        actionBar.setDisplayShowTitleEnabled(true);
        //设置标题可以被点击
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Log.i("aaa","返回按钮");
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
