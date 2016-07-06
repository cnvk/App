package my.microsoft.com.day02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/**
 * 下载图片
 * 点击按钮下载
 * 下载开始,Activity启动一个服务
 * onStartCommand的方法中耗时操作（下载，保存SD卡）
 *
 * Notification结合,使用通知显示下载进度
 */
public class StartServiceActivity05 extends AppCompatActivity implements View.OnClickListener{
    private String urlstr="http://img12.360buyimg.com/da/jfs/t2824/275/2525627243/59979/ed64a20c/5768f099N738e83e2.jpg";
    private Button btn1;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service05);
        btn1= (Button) this.findViewById(R.id.StartServiceActivity05_btn01);
        btn1.setOnClickListener(this);
         intent=new Intent(this,StartService05.class);
        intent.putExtra("urlstr",urlstr);

    }

    @Override
    public void onClick(View view) {
        startService(intent);
    }
}
